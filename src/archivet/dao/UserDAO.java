package archivet.dao;

import archivet.config.DBConnection;
import archivet.model.OwnerDTO;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class UserDAO implements IUserDAO {

    // --- Consultas SQL ---
    // --- Finds
    private static final String FIND_ALL_OWNERS_QUERY = """
        SELECT u.*, o.billing_address 
        FROM user AS u 
        JOIN owner AS o 
        ON u.user_id = o.user_id
        WHERE u.role_name = 'OWNER' AND u.is_active = TRUE; """;

    private static final String FIND_ALL_DOCTORS_QUERY = """
        SELECT u.*, v.license_number, v.specialization, v.shift_schedule
        FROM user AS u 
        JOIN vet_doctor AS v 
        ON u.user_id = v.user_id
        WHERE u.role_name = 'VETDOCTOR' AND u.is_active = TRUE; """;

    private static final String FIND_USER_JOIN_QUERY = """
        SELECT u.*, o.billing_address, v.license_number, v.specialization, v.shift_schedule                         
        FROM user AS u
        LEFT JOIN owner AS o
        ON u.user_id = o.user_id
        LEFT JOIN vet_doctor as v
        ON u.user_id = v.user_id """;
    
    private static final String FIND_BY_ID_FULL_SQL = FIND_USER_JOIN_QUERY + " WHERE u.user_id = ?";

    private static final String FIND_BY_CREDENTIALS_SQL = FIND_USER_JOIN_QUERY + """
        WHERE u.email = ?
        AND u.password_hash = ?
        AND u.is_active = TRUE; """;

    // --- Inserts
    private static final String INSERT_USER_SQL = """
        INSERT INTO 
        user (first_name, last_name, email, password_hash, phone_number, sex, role_name) 
        VALUES (?, ?, ?, ?, ?, ?, ?); """;

    private static final String INSERT_OWNER_SQL = """
        INSERT INTO 
        owner (user_id, billing_address) 
        VALUES (?, ?); """;

    private static final String INSERT_VET_DOCTOR_SQL = """
        INSERT INTO 
        vet_doctor (user_id, license_number, specialization, shift_schedule) 
        VALUES (?, ?, ?, ?); """;

    // --- Updates
    private static final String UPDATE_USER_SQL = """
        UPDATE user 
        SET first_name=?, last_name=?, email=?, password_hash=?, phone_number=?, sex=?, is_active=?
        WHERE user_id=?; """;

    private static final String UPDATE_OWNER_SQL = """
        UPDATE owner 
        SET billing_address=? 
        WHERE user_id=?; """;

    private static final String UPDATE_VET_DOCTOR_SQL = """
        UPDATE vet_doctor 
        SET license_number=?, specialization=?, shift_schedule=? 
        WHERE user_id=?; """;

    // --- Self Delete
    private static final String DEACTIVATE_USER_SQL = """
        UPDATE User 
        SET is_active = FALSE 
        WHERE user_id = ?; """;

    private UserDTO buildUserDTO(ResultSet rs) throws SQLException {

        // 1. Leer datos comunes de la tabla USER
        int id = rs.getInt("user_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String passwordHash = rs.getString("password_hash");
        String phoneNumber = rs.getString("phone_number");

        // Convertir el String de la DB a SexEnum
        UserDTO.SexEnum sex = UserDTO.SexEnum.valueOf(rs.getString("sex"));

        String roleName = rs.getString("role_name").toUpperCase();
        boolean isActive = rs.getBoolean("is_active");

        // 2. Decidir que subclase crear (Polimorfismo)
        if ("OWNER".equals(roleName)) {
            String billingAddress = rs.getString("billing_address");

            OwnerDTO owner = new OwnerDTO(billingAddress, id, firstName, lastName, email, passwordHash, phoneNumber, sex, isActive);
            owner.setUserId(id);
            return owner;
        } else if ("VETDOCTOR".equals(roleName)) {
            String licenseNumber = rs.getString("license_number");
            String specialization = rs.getString("specialization");
            String shiftSchedule = rs.getString("shift_schedule");

            VetDoctorDTO doctor = new VetDoctorDTO(licenseNumber, specialization, shiftSchedule, id, firstName, lastName, email, passwordHash, phoneNumber, sex, isActive);
            doctor.setUserId(id);
            return doctor;
        }
        return null;
    }

    // ------------------------------------------------------------------
    // 1. SAVE (Crear/Guardar) - Requiere Transacción
    // ------------------------------------------------------------------
    @Override
    public boolean save(UserDTO user) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 1. INICIA TRANSACCIÓN

            // A. Insertar en la tabla USER y obtener ID
            try (PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPlainPassword());
                stmt.setString(5, user.getPhoneNumber());
                stmt.setString(6, user.getSex().name());
                stmt.setString(7, user.getRoleName());

                if (stmt.executeUpdate() == 0) {
                    throw new SQLException("Fallo al crear el usuario");
                }

                try (ResultSet generateKeys = stmt.getGeneratedKeys()) {
                    if (generateKeys.next()) {
                        user.setUserId(generateKeys.getInt(1));
                    } else {
                        throw new SQLException("Fallo al obtener ID del usuario");
                    }
                }
            }

            // B. Insertar en la tabla específica (OWNER o VET_DOCTOR)
            if (user instanceof OwnerDTO owner) {
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_OWNER_SQL)) {
                    stmt.setInt(1, owner.getUserId());
                    stmt.setString(2, owner.getBillingAddress());
                    stmt.executeUpdate();
                }
            } else if (user instanceof VetDoctorDTO doctor) {
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_VET_DOCTOR_SQL)) {
                    stmt.setInt(1, doctor.getUserId());
                    stmt.setString(2, doctor.getLicenseNumber());
                    stmt.setString(3, doctor.getSpecialization());
                    stmt.setString(4, doctor.getShiftSchedule());
                    stmt.executeUpdate();
                }
            }

            conn.commit(); // 2. CONFIRMA LA TRANSACCIÓN
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // 3. DESHACE EN CASO DE ERROR
            }
            System.err.println("Error transaccion al guardar usuario: " + e.getMessage());
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
    }

    // ------------------------------------------------------------------
    // 2. UPDATE (Actualizar) - Requiere Transacción
    // ------------------------------------------------------------------
    @Override
    public boolean update(UserDTO user) throws SQLException {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            boolean success = false;

            // A. Actualizar la tabla USER (Datos Comunes)
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_SQL)) {
                stmt.setInt(1, user.getUserId());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPlainPassword());
                stmt.setString(6, user.getPhoneNumber());
                stmt.setString(7, user.getSex().name());
                stmt.setBoolean(8, user.getIsActive());
            }

            // B. Actualizar la tabla Específica (OWNER o VET_DOCTOR)
            if (user instanceof OwnerDTO owner) {
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_OWNER_SQL)) {
                    stmt.setInt(1, owner.getUserId());
                    stmt.setString(2, owner.getBillingAddress());
                    stmt.executeUpdate();
                }
            } else if (user instanceof VetDoctorDTO doctor) {
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_VET_DOCTOR_SQL)) {
                    stmt.setInt(1, doctor.getUserId());
                    stmt.setString(2, doctor.getLicenseNumber());
                    stmt.setString(3, doctor.getSpecialization());
                    stmt.setString(4, doctor.getShiftSchedule());
                    stmt.executeUpdate();
                }
            }

            conn.commit();
            return success;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Error transaccional al actualizar usuario: " + e.getMessage());
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }

    }

    // ------------------------------------------------------------------
    // 3. SOFT DELETE (Actualiza isActive a FALSE) - Requiere Transacción
    // ------------------------------------------------------------------
    @Override
    public boolean delete(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(DEACTIVATE_USER_SQL)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar usuario: " + e.getMessage());
            throw e;
        }
    }

    // ------------------------------------------------------------------
    // 4. READ (findAll, findAllDoctors, findAllOwners) - Implementación simplificada
    // ------------------------------------------------------------------
    @Override
    public List<VetDoctorDTO> findAllDoctors() throws SQLException {
        List<VetDoctorDTO> doctors = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_DOCTORS_QUERY); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserDTO user = buildUserDTO(rs);

                if (user instanceof VetDoctorDTO) {
                    doctors.add((VetDoctorDTO) user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar propietarios: " + e.getMessage());
            throw e;
        }
        return doctors;
    }

    @Override
    public List<OwnerDTO> findAllOwners() throws SQLException {
        List<OwnerDTO> owners = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_OWNERS_QUERY); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserDTO user = buildUserDTO(rs);

                if (user instanceof OwnerDTO) {
                    owners.add((OwnerDTO) user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar propietarios: " + e.getMessage());
            throw e;
        }
        return owners;
    }

    @Override
    public UserDTO findById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_FULL_SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return buildUserDTO(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public VetDoctorDTO findByCredentials(String email, String passwordHash) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_BY_CREDENTIALS_SQL)) {

            stmt.setString(1, email);
            stmt.setString(2, passwordHash);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserDTO user = buildUserDTO(rs);

                    // Aplica la restriccion de que solo VetDoctor puede iniciar sesion
                    if (user instanceof VetDoctorDTO) {
                        return (VetDoctorDTO) user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en findByCredentials: " + e.getMessage());
            throw e;
        }
        return null;
    }

}
