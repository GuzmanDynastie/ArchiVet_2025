package archivet;

import archivet.config.DBConnection;
import archivet.controller.PetController;
import archivet.controller.UserController;
import archivet.model.CatDTO;
import archivet.model.PetDTO;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;
import archivet.service.PetService;
import archivet.service.UserService;
import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class ArchiVet {

    public static void main(String[] args) {

        UserService userService = new UserService();
        UserController user = new UserController(userService);

        System.out.println(user.handleGetUserById(1));
        System.out.println(user.handleLogin("mario@gmail.com", "MarioGay"));

        PetService petService = new PetService();
        PetController pet = new PetController(petService);

//        pet.handleCatRegistration("Kenzo", "Negro y Cafe", PetDTO.SexEnum.MALE, PetDTO.SpecieEnum.CAT, false, LocalDate.now(), 1);
        System.out.println(pet.handleGetPetById(1));
        System.out.println(pet.handleGetPetsByOwnerId(1));

        DBConnection.closeConnection();
    }

}
