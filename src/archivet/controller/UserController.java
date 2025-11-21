package archivet.controller;

import archivet.model.OwnerDTO;
import archivet.model.UserDTO;
import archivet.model.UserDTO.SexEnum;
import archivet.model.VetDoctorDTO;
import archivet.service.IUserService;
import archivet.service.UserService;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = new UserService();
    }

    // ------------------------------------------------------------------
    // 1. AUTENTICACIÓN (LOGIN)
    // ------------------------------------------------------------------
    public VetDoctorDTO handleLogin(String email, String plainPassword) {
        try {
            return userService.getDoctorByCredentials(email, plainPassword);
        } catch (Exception e) {
            System.err.println("Error de LOGIN en controller: " + e.getMessage());
            // Aqui se maneja el feedback a la view (algun JOptionPane)
            return null;
        }
    }

    // ------------------------------------------------------------------
    // 2. REGISTRO Y ACTUALIZACIÓN
    // ------------------------------------------------------------------
    public boolean handleDoctorRegistration(String firstName, String lastName, String email, String plainPassword, String phoneNumber, SexEnum sex, String license, String specialization, String shift) {
        try {
            VetDoctorDTO doctor = new VetDoctorDTO(license, specialization, shift, firstName, lastName, email, plainPassword, phoneNumber, sex);

            return userService.registerUser(doctor, plainPassword);
        } catch (Exception e) {
            System.err.println("Error al registrar Doctor: " + e.getMessage());
            return false;
        }
    }

    public boolean handleOwnerRegistration(String firstName, String lastName, String email, String phoneNumber, SexEnum sex, String billingAddress) {
        try {
            OwnerDTO owner = new OwnerDTO(billingAddress, firstName, lastName, email, phoneNumber, sex);

            return userService.registerUser(owner, "");
        } catch (Exception e) {
            System.err.println("Error al registrar Propietario: " + e.getMessage());
            return false;
        }
    }

    public boolean handleUpdateUser(UserDTO user) {
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean handleDeactivateUser(int userId) {
        try {
            return userService.deactivateUser(userId);
        } catch (Exception e) {
            System.err.println("Error al desactivar usuario: " + e.getMessage());
            return false;
        }
    }

    public UserDTO handleGetUserById(int userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            System.err.println("Error en el Controller al buscar usuario por ID " + e.getMessage());
            return null;
        }
    }

    // ------------------------------------------------------------------
    // 3. LECTURA DE DATOS PARA VISTAS
    // ------------------------------------------------------------------
    public List<OwnerDTO> loadActiveOwners() {
        try {
            return userService.getAllActiveOwners();
        } catch (Exception e) {
            System.err.println("Error cargando propietarios: " + e.getMessage());
            return List.of();
        }
    }

    public List<VetDoctorDTO> loadDoctors() {
        try {
            return userService.getAllDoctors();
        } catch (Exception e) {
            System.err.println("Error cargando doctores: " + e.getMessage());
            return List.of();
        }
    }

}
