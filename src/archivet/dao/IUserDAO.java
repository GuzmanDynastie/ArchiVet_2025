package archivet.dao;

import archivet.model.OwnerDTO;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IUserDAO {

    /**
     * Guarda un nuevo usuario en la base de datos. Este método debe realizar
     * una transacción: insertar en la tabla USER y luego en la tabla específica
     * (OWNER o VET_DOCTOR).
     *
     * @param user El objeto UserDTO (OwnerDTO o VetDoctorDTO) a guardar.
     * @return true si la inserción fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean save(UserDTO user) throws SQLException;

    /**
     * Busca un usuario por su ID primario. El DAO debe leer el rol (role_name)
     * para instanciar la subclase correcta.
     *
     * @param id El ID (userId) del usuario a buscar.
     * @return El objeto UserDTO (OwnerDTO o VetDoctorDTO), o null si no se
     * encuentra.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    UserDTO findById(int id) throws SQLException;

    /**
     * Busca un VETERINARIO por sus credenciales (email y password hash) para el
     * inicio de sesión. La lógica de negocio implica que solo los VetDoctorDTO
     * pueden ser devueltos exitosamente para acceder a la aplicación de
     * escritorio.
     *
     * @param email El correo electrónico del usuario.
     * @param passwordHash El hash de la contraseña del usuario.
     * @return El objeto VetDoctorDTO autenticado, o null si no se encuentra o
     * no es doctor.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    VetDoctorDTO findByCredentials(String email, String passwordHash) throws SQLException;

    /**
     * Modifica los datos de un usuario existente (tablas USER y la tabla
     * específica).
     *
     * @param user El objeto UserDTO con los datos actualizados.
     * @return true si la actualización fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean update(UserDTO user) throws SQLException;

    /**
     * Elimina un usuario por su ID primario. Las tablas dependientes (OWNER,
     * VET_DOCTOR) deben ser eliminadas en cascada.
     *
     * @param id El ID (userId) del usuario a eliminar.
     * @return true si la eliminación fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean delete(int id) throws SQLException;

    /**
     * Obtiene una lista de todos los usuarios con el rol de VETERINARIO.
     *
     * @return Lista de VetDoctorDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<VetDoctorDTO> findAllDoctors() throws SQLException;

    /**
     * Obtiene una lista de todos los usuarios con el rol de PROPIETARIO.
     *
     * @return Lista de OwnerDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<OwnerDTO> findAllOwners() throws SQLException;
}
