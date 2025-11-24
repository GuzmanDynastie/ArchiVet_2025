package archivet.controller;

import archivet.model.CatDTO;
import archivet.model.DogDTO;
import archivet.model.interfaces.PetDTO;
import archivet.model.interfaces.PetDTO.SexEnum;
import archivet.service.interfaces.IPetService;
import archivet.service.PetService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class PetController {
    
    private final IPetService petService;
    
    public PetController(IPetService petService) {
        this.petService = new PetService();
    }

    // ------------------------------------------------------------------
    // 1. REGISTRO Y ACTUALIZACIÓN
    // ------------------------------------------------------------------
    public boolean handleDogRegistration(String name, String coatColor, SexEnum sex, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, String trainingLevel) {
        try {
            DogDTO dog = new DogDTO(ownerId, name, birthDate, sex, isStirilazed, coatColor, speciesType, sex, trainingLevel);
            return petService.registerPet(dog);
        } catch (Exception e) {
            System.err.println("Error al registrar perro: " + e.getMessage());
            return false;
        }
    }
    
    public boolean handleCatRegistration(String name, String coatColor, SexEnum sex, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean isIndoor) {
        try {
            CatDTO cat = new CatDTO(ownerId, name, birthDate, sex, isStirilazed, coatColor, speciesType, sex, isIndoor);
            return petService.registerPet(cat);
        } catch (Exception e) {
            System.err.println("Error al registrar gato: " + e.getMessage());
            return false;
        }
    }
    
    public boolean handleUpdatePet(PetDTO pet) {
        try {
            return petService.updatePet(pet);
        } catch (Exception e) {
            System.err.println("Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }
    
    public boolean handleDeactivatePet(int petId) {
        try {
            return petService.deactivatePet(petId);
        } catch (Exception e) {
            System.err.println("Error al desactivar mascota: " + e.getMessage());
            return false;
        }
    }
    
    public boolean handleActivatePet(int petId) {
        try {
            return petService.activatePet(petId);
        } catch (Exception e) {
            System.err.println("Error al activar mascota: " + e.getMessage());
            return false;
        }
    }
    
    public PetDTO handleGetPetById(int petId) {
        try {
            return petService.getPetById(petId);
        } catch (Exception e) {
            System.err.println("Error en el Controller al buscar mascota por ID " + e.getMessage());
            return null;
        }
    }
    
    public List<PetDTO> handleGetPetsByOwnerId(int ownerId) {
        try {
            return petService.getPetsByOwnerId(ownerId);
        } catch (Exception e) {
            System.err.println("Error en el Controller al buscar mascotas por ID del propietario " + e.getMessage());
            return List.of();
        }
    }
    
    // ------------------------------------------------------------------
    // 2. LECTURA DE DATOS PARA VISTAS
    // ------------------------------------------------------------------
    public List<PetDTO> loadActivatePets() {
        try {
            return petService.getAllPets();
        } catch (Exception e) {
            System.err.println("Error cargando perros " + e.getMessage());
            return List.of();
        }
    }
}
