package archivet;

import archivet.config.DBConnection;
import archivet.controller.UserController;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;

/**
 *
 * @author guzman-dynastie
 */
public class ArchiVet {

    public static void main(String[] args) {

        UserController user = new UserController();
        VetDoctorDTO doctor = new VetDoctorDTO("123456", "Mata perros", "7:00 - 13:00 Lunes a Viernes", 2, "Mario Jesus Armando", "Solis Nieves", "mario@gmail.com", "MataPerros", "3366633666", UserDTO.SexEnum.MALE, true);

        System.out.println(user.handleUpdateUser(doctor));
        System.out.println(user.handleGetUserById(2));
        System.out.println(user.handleLogin("mario@gmail.com", "MarioGay"));

        DBConnection.closeConnection();
    }

}
