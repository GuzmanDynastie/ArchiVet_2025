package archivet.dao;

import archivet.model.UserDTO;
import archivet.model.OwnerDTO;
import archivet.model.VetDoctorDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IUserDAO {

    // Guarda un nuevo usuario en la base de datos.
    boolean save(UserDTO user) throws SQLException;
    
    // Modifica los datos de un usuario existente (tablas USER y la tabla específica).
    boolean update(UserDTO user) throws SQLException;
    
    // Elimina logicamente un usuario por su ID primario. Las tablas dependientes (OWNER, VET_DOCTOR) deben ser eliminadas en cascada.
    boolean delete(int id) throws SQLException;

    // Busca un usuario por su ID primario.
    UserDTO findById(int id) throws SQLException;
    
    // Busca un usuario por su email.
    UserDTO findByEmail(String email) throws SQLException;

    // Busca un VETERINARIO por sus credenciales (email y password hash) para el inicio de sesión.
    VetDoctorDTO findByCredentials(String email, String passwordHash) throws SQLException;

    // Obtiene una lista de todos los usuarios con el rol de VETERINARIO.
    List<VetDoctorDTO> findAllDoctors() throws SQLException;

    // Obtiene una lista de todos los usuarios con el rol de PROPIETARIO.
    List<OwnerDTO> findAllOwners() throws SQLException;
    
}
