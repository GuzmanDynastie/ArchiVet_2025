package archivet.service;

import archivet.model.OwnerDTO;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IUserService {

    // Registra un nuevo usuario (Owner o Doctor) en el sistema.
    boolean registerUser(UserDTO user, String plainPassword) throws Exception;
    
    // Modifica los datos de un usuario existente en las tablas USER y su subclase especifica.
    boolean updateUser(UserDTO user) throws Exception;
    
    // Realiza la ELIMINACION LOGICA (Soft Delete) de un usuario.
    boolean deactivateUser(int userId) throws Exception;

    // Busca un usuario (Owner o Doctor) por su ID primario.
    UserDTO getUserById(int userId) throws Exception;

    // Intenta autenticar un VETERINARIO.
    VetDoctorDTO getDoctorByCredentials(String email, String plainPassword) throws Exception;

    // Obtiene una lista de todos los usuarios con el rol de VETERINARIO activos.
    List<VetDoctorDTO> getAllDoctors() throws Exception;

    // Obtiene una lista de todos los usuarios con el rol de PROPIETARIO activos.
    List<OwnerDTO> getAllActiveOwners() throws Exception;
    
}
