package archivet.service;

import archivet.model.PetDTO;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetService {

    // Guarda una nueva mascota en la base de datos.
    boolean registerPet(PetDTO pet) throws Exception;

    // Modifica los datos de una mascota existente.
    boolean updatePet(PetDTO pet) throws Exception;

    // Elimina logicamente una mascota y todos sus registros asociados.
    boolean deactivatePet(int petId) throws Exception;

    // Obtiene una lista de todas las mascotas registradas en el sistema.
    List<PetDTO> getAllPets() throws Exception;

    // Busca una mascota por su ID.
    PetDTO getPetById(int petId) throws Exception;

    // Busca todas las mascotas asociadas a un dueño específico.
    List<PetDTO> getPetsByOwnerId(int ownerId) throws Exception;

}
