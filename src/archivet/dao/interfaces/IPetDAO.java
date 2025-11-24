package archivet.dao.interfaces;

import archivet.model.CatDTO;
import archivet.model.DogDTO;
import archivet.model.interfaces.PetDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetDAO {

    // Guarda una nueva mascota en la base de datos.
    boolean save(PetDTO pet) throws SQLException;

    // Modifica los datos de una mascota existente (tablas PET y la tabla específica).
    boolean update(PetDTO pet) throws SQLException;

    // Elimina logicamente una mascota por su ID primario. Las tablas dependientes (DOG, CAT) deben ser eliminadas en cascada.
    boolean delete(int id) throws SQLException;
    
    // Activa logicamente una mascota por su ID primario. Las tablas dependientes (DOG, CAT) deben ser eliminadas en cascada.
    boolean activate(int id) throws SQLException;

    // Busca una mascota por su ID primario.
    PetDTO findById(int id) throws SQLException;

    // Obtiene una lista de todas las mascotas por el ID del propietario.
    List<PetDTO> findAllPetsByOwnerId(int ownerId) throws SQLException;

    // Obtiene una lista de todos los perros.
    List<DogDTO> findAllDogs() throws SQLException;

    // Obtiene una lista de todos los gatos.
    List<CatDTO> findAllCats() throws SQLException;

    // Obtiene una lista de todos las mascotas.
    List<PetDTO> findAllPets() throws SQLException;

}
