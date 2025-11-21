package archivet.dao;

import archivet.config.DBConnection;
import archivet.model.CatDTO;
import archivet.model.DogDTO;
import archivet.model.PetDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class PetDAO implements IPetDAO {

    // --- Consultas SQL ---
    // --- Finds
    private static final String FIND_ALL_DOGS_QUERY = """
        SELECT p.*
        FROM pet AS p
        JOIN dog AS d                                              
        ON p.pet_id = d.pet_id                                              
        WHERE p.species_type = 'Dog' AND p.is_active = TRUE; """;

    private static final String FIND_ALL_CATS_QUERY = """
        SELECT p.*                                            
        FROM pet AS p                                              
        JOIN cat AS c                                              
        ON p.pet_id = c.pet_id                                              
        WHERE p.species_type = 'Cat' AND p.is_active = TRUE; """;

    private static final String FIND_PETS_JOIN_QUERY = """
        SELECT p.*, c.*, d.*                                            
        FROM pet AS p                                              
        JOIN cat AS c                                              
        ON p.pet_id = c.pet_id 
        JOIN dog AS d                                              
        ON p.pet_id = d.pet_id """;

    private static final String FIND_PET_JOIN_QUERY = """
        SELECT p.*, o.*                                             
        FROM pet AS p                                              
        LEFT JOIN owner AS o                                              
        ON p.owner_id = o.user_id """;

    private static final String FIND_BY_ID_FULL_SQL = FIND_PET_JOIN_QUERY + " WHERE p.pet_id = ?";

    private static final String FIND_ALL_PETS_BY_OWNER_ID_FULL_SQL = FIND_PET_JOIN_QUERY + " WHERE p.owner_id = ?";

    // --- Inserts
    private static final String INSERT_PET_SQL = """
        INSERT INTO                                         
        pet (owner_id, name, birth_date, sex, is_sterilized, coat_color, species_type)                                         
        VALUES (?, ?, ?, ?, ?, ?, ?); """;

    private static final String INSERT_DOG_SQL = "INSERT INTO dog (pet_id) VALUES (?); ";

    private static final String INSERT_CAT_SQL = "INSERT INTO cat (pet_id) VALUES (?); ";

    // --- Updates
    private static final String UPDATE_PET_SQL = """
        UPDATE pet                                         
        SET owner_id=?, name=?, birth_date=?, sex=?, is_sterilized=?, coat_color=?, species_type=?                                         
        WHERE pet_id = ?; """;

    private static final String UPDATE_DOG_SQL = "";
    private static final String UPDATE_CAT_SQL = "";

    // --- Soft Delete
    private static final String DEACTIVATE_PET_SQL = "UPDATE pet SET is_active = FALSE WHERE pet_id = ?; ";

    private PetDTO buildPetDTO(ResultSet rs) throws SQLException {

        // 1. Leer datos comunes de la tabla PET
        int idPet = rs.getInt("pet_id");
        int idOwner = rs.getInt("owner_id");
        String name = rs.getString("name");

        // Convertir el Date de la DB a LocalDate
        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();

        // Convertir el String de la DB a SexEnum
        PetDTO.SexEnum sex = PetDTO.SexEnum.valueOf(rs.getString("sex"));

        boolean isSterilized = rs.getBoolean("is_sterilized");
        String coatColor = rs.getString("coat_color");
        String speciesType = rs.getString("species_type").toUpperCase();
        boolean isActive = rs.getBoolean("is_active");

        // 2. Decidir que subclase crear (Polimorfismo)
        if ("DOG".equals(speciesType)) {
            DogDTO dog = new DogDTO(idPet, name, coatColor, sex, PetDTO.SpecieEnum.DOG, isSterilized, birthDate, idOwner, isActive);
            dog.setPetId(idPet);
            return dog;
        } else if ("CAT".equals(speciesType)) {
            CatDTO cat = new CatDTO(idPet, name, coatColor, sex, PetDTO.SpecieEnum.CAT, isSterilized, birthDate, idOwner, isActive);
            cat.setPetId(idPet);
            return cat;
        }
        return null;
    }

    // ------------------------------------------------------------------
    // 1. SAVE (Crear/Guardar) - Requiere Transacción
    // ------------------------------------------------------------------
    @Override
    public boolean save(PetDTO pet) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 1. INICIA TRANSACCION

            try (PreparedStatement stmt = conn.prepareStatement(INSERT_PET_SQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, pet.getOwnerId());
                stmt.setString(2, pet.getName());
                stmt.setDate(3, Date.valueOf(pet.getBirthDate()));
                stmt.setString(4, pet.getSex().name());
                stmt.setBoolean(5, pet.getIsSterilized());
                stmt.setString(6, pet.getCoatColor());
                stmt.setString(7, pet.getSpeciesType().name());

                if (stmt.executeUpdate() == 0) {
                    throw new SQLException("Fallo al crear la mascota");
                }

                try (ResultSet generateKeys = stmt.getGeneratedKeys()) {
                    if (generateKeys.next()) {
                        pet.setPetId(generateKeys.getInt(1));
                    } else {
                        throw new SQLException("Fallo al obtener ID de la mascota");
                    }
                }
            }

            // B. Insertar en la tabla especifica (DOG o CAT)
            if (pet instanceof DogDTO dog) {
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_DOG_SQL)) {
                    stmt.setInt(1, dog.getPetId());
                    stmt.executeUpdate();
                }
            } else if (pet instanceof CatDTO cat) {
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_CAT_SQL)) {
                    stmt.setInt(1, cat.getPetId());
                    stmt.executeUpdate();
                }
            }

            conn.commit(); // 2. CONFIRMA LA TRANSACCION
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // 3. DESHACE EN CASO DE ERROR
            }
            System.err.println("Error transaccion al guardar mascota: " + e.getMessage());
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
    public boolean update(PetDTO pet) throws SQLException {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            boolean success = false;

            // A. Actualizar la tabla PET (Datos Comunes)
            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_PET_SQL)) {
                stmt.setInt(1, pet.getOwnerId());
                stmt.setString(2, pet.getName());
                stmt.setDate(3, Date.valueOf(pet.getBirthDate()));
                stmt.setString(4, pet.getSex().name());
                stmt.setBoolean(5, pet.getIsSterilized());
                stmt.setString(6, pet.getCoatColor());
                stmt.setString(7, pet.getSpeciesType().name());
                stmt.setInt(8, pet.getPetId());

                success = stmt.executeUpdate() > 0;
            }

            // B. Actualizar la tabla Especifica (DOG o CAT)
//            if (pet instanceof DogDTO dog) {
//                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_DOG_SQL)) {
//                }
//            } else if (pet instanceof CatDTO cat) {
//                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_CAT_SQL)) {
//                }
//            }
            conn.commit();
            return success;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            System.err.println("Error transaccional al actualizar mascota: " + e.getMessage());
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
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(DEACTIVATE_PET_SQL)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar mascota: " + e.getMessage());
            throw e;
        }
    }

    // ------------------------------------------------------------------
    // 4. READ (findById, findAllPetsByOwnerId, findAllPets, findAllDogs, findAllCats) - Implementación simplificada
    // ------------------------------------------------------------------
    @Override
    public PetDTO findById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_FULL_SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return buildPetDTO(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mascota por ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public List<PetDTO> findAllPetsByOwnerId(int ownerId) throws SQLException {
        List<PetDTO> pets = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_PETS_BY_OWNER_ID_FULL_SQL)) {
            stmt.setInt(1, ownerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pets.add(buildPetDTO(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mascotas por ID del propietario: " + e.getMessage());
            throw e;
        }
        return pets;
    }

    @Override
    public List<PetDTO> findAllPets() throws SQLException {
        List<PetDTO> pets = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_PETS_JOIN_QUERY); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PetDTO pet = buildPetDTO(rs);

                if (pet != null) {
                    pets.add(pet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar perros: " + e.getMessage());
            throw e;
        }
        return pets;
    }

    @Override
    public List<DogDTO> findAllDogs() throws SQLException {
        List<DogDTO> dogs = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_DOGS_QUERY); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PetDTO pet = buildPetDTO(rs);

                if (pet instanceof DogDTO) {
                    dogs.add((DogDTO) pet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar perros: " + e.getMessage());
            throw e;
        }
        return dogs;
    }

    @Override
    public List<CatDTO> findAllCats() throws SQLException {
        List<CatDTO> cats = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_CATS_QUERY); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PetDTO pet = buildPetDTO(rs);

                if (pet instanceof DogDTO) {
                    cats.add((CatDTO) pet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar gatos: " + e.getMessage());
            throw e;
        }
        return cats;
    }

}
