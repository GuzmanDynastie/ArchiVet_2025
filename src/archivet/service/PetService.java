package archivet.service;

import archivet.dao.PetDAO;
import archivet.model.PetDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public class PetService implements IPetService {

    private final PetDAO petDAO;

    public PetService() {
        this.petDAO = new PetDAO();
    }

    // --- Escritura
    @Override
    public boolean registerPet(PetDTO pet) throws Exception {
        try {
            return petDAO.save(pet);
        } catch (SQLException e) {
            throw new Exception("Error al registrar la mascota: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updatePet(PetDTO pet) throws Exception {
        try {
            return petDAO.update(pet);
        } catch (SQLException e) {
            throw new Exception("Error al actualizar la mascota");
        }
    }

    @Override
    public boolean deactivatePet(int petId) throws Exception {
        try {
            return petDAO.delete(petId);
        } catch (SQLException e) {
            throw new Exception("Error al desactivar la mascota", e);
        }
    }

    @Override
    public List<PetDTO> getAllPets() throws Exception {
        try {
            return petDAO.findAllPets();
        } catch (SQLException e) {
            throw new Exception("Error al obtener la lista de mascotas");
        }
    }

    @Override
    public PetDTO getPetById(int petId) throws Exception {
        try {
            PetDTO pet = petDAO.findById(petId);
            return pet;
        } catch (SQLException e) {
            throw new Exception("Error interno del sistema al buscar mascota por ID", e);
        }
    }

    @Override
    public List<PetDTO> getPetsByOwnerId(int ownerId) throws Exception {
        try {
            return petDAO.findAllPetsByOwnerId(ownerId);
        } catch (SQLException e) {
            throw new Exception("Error interno del sistema al buscar propietario por ID");
        }
    }

}
