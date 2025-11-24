package archivet.service;

import archivet.service.interfaces.IUserService;
import archivet.dao.UserDAO;
import archivet.model.OwnerDTO;
import archivet.model.interfaces.UserDTO;
import archivet.model.VetDoctorDTO;
import archivet.util.PasswordUtil;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class UserService implements IUserService {
    
    private final UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }

    // --- Escritura
    @Override
    public boolean registerUser(UserDTO user, String plainPassword) throws Exception {
        if (user instanceof VetDoctorDTO) {
            if (plainPassword == null || plainPassword.isEmpty()) {
                throw new Exception("La contraseña no puede estar vacia para un doctor.");
            }
            
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);
            user.setPasswordHash(hashedPassword);
        }
        
        try {
            return userDAO.save(user);
        } catch (SQLException e) {
            throw new Exception("Error al registrar el usuario: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean updateUser(UserDTO user) throws Exception {
        try {
            String hashedPassword = PasswordUtil.hashPassword(user.getPasswordHash());
            user.setPasswordHash(hashedPassword);
            return userDAO.update(user);
        } catch (SQLException e) {
            throw new Exception("Error al actualizar el usuario.");
        }
    }
    
    @Override
    public boolean deactivateUser(int userId) throws Exception {
        try {
            return userDAO.delete(userId);
        } catch (SQLException e) {
            throw new Exception("Error al desactivar el usuario.", e);
        }
    }
    
    @Override
    public boolean activateUser(int userId) throws Exception {
        try {
            return userDAO.activate(userId);
        } catch (SQLException e) {
            throw new Exception("Error al activar el usuario.", e);
        }
    }

    // --- Lectura y Autenticacion
    @Override
    public VetDoctorDTO getDoctorByCredentials(String email, String plainPassword) throws Exception {
        UserDTO user = userDAO.findByEmail(email);
        
        if (user instanceof VetDoctorDTO doctor) {
            if (PasswordUtil.checkPassword(plainPassword, doctor.getPasswordHash())) {
                if (doctor.getIsActive()) {
                    return doctor;
                } else {
                    throw new Exception("El usuario esta inactivo. Contacta al administrador.");
                }
            }
        }
        return null;
    }
    
    @Override
    public List<OwnerDTO> getAllActiveOwners() throws Exception {
        try {
            return userDAO.findAllOwners();
        } catch (SQLException e) {
            throw new Exception("Error al obtener la lista de propietarios.", e);
        }
    }
    
    @Override
    public List<VetDoctorDTO> getAllDoctors() throws Exception {
        try {
            return userDAO.findAllDoctors();
        } catch (SQLException e) {
            throw new Exception("Error al obtener la lista de doctores.", e);
        }
    }
    
    @Override
    public UserDTO getUserById(int userId) throws Exception {
        try {
            UserDTO user = userDAO.findById(userId);
            
            return user;
        } catch (SQLException e) {
            throw new Exception("Error interno del sistema al buscar usuario por ID.", e);
        }
    }
    
}
