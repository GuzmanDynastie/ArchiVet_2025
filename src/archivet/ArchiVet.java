package archivet;

import archivet.config.DBConnection;
import archivet.controller.PetController;
import archivet.controller.UserController;
import archivet.model.CatDTO;
import archivet.model.interfaces.PetDTO;
import archivet.model.interfaces.UserDTO;
import archivet.model.VetDoctorDTO;
import archivet.service.PetService;
import archivet.service.UserService;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

/**
 *
 * @author guzman-dynastie
 */
public class ArchiVet {

    public static void main(String[] args) {

        UserService userService = new UserService();
        UserController user = new UserController(userService);

//        System.out.println(user.handleGetUserById(1));
        System.out.println(user.handleLogin("mario@gmail.com", "MarioGay"));

        PetService petService = new PetService();
        PetController pet = new PetController(petService);
        
//        System.out.println(pet.handleActivatePet(1));

//        CatDTO cat = new CatDTO(1, 1, "Kenzo", LocalDate.of(2025,11,20), PetDTO.SexEnum.MALE, false, "Negro y Cafe", "Cat", CatDTO.BreedEnum.MESTIZO, true, true);
//        System.out.println(pet.handleUpdatePet(cat));

        System.out.println(pet.handleGetPetById(1));
//        System.out.println(pet.handleGetPetsByOwnerId(1));

        DBConnection.closeConnection();
    }

}
