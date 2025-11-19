package archivet.service;

import archivet.model.PetDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetService {

    // Guarda una nueva mascota en la base de datos.
    boolean save(PetDTO pet) throws SQLException;

    // Busca una mascota por su ID.
    PetDTO findById(int id) throws SQLException;

    // Busca todas las mascotas asociadas a un dueño específico.
    List<PetDTO> findByOwnerId(int ownerId) throws SQLException;

    // Modifica los datos de una mascota existente.
    boolean update(PetDTO pet) throws SQLException;

    // Elimina logicamente una mascota y todos sus registros asociados.
    boolean softDelete(int id) throws SQLException;

    // Obtiene una lista de todas las mascotas registradas en el sistema.
    List<PetDTO> findAll() throws SQLException;
    
}
