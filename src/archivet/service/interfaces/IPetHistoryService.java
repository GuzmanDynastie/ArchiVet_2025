package archivet.service.interfaces;

import archivet.model.PetHistoryDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetHistoryService {

    // Guarda un nuevo registro de historial clínico en la base de datos.
    boolean save(PetHistoryDTO record) throws SQLException;

    // Busca un registro de historial clínico individual por su ID primario.
    PetHistoryDTO findById(int id) throws SQLException;

    // Recupera todos los registros de historial clínico asociados a una mascota.
    List<PetHistoryDTO> findByPetId(int petId) throws SQLException;

    // Recupera una lista de todos los registros de historial clínico en el sistema.
    List<PetHistoryDTO> findAll() throws SQLException;

    // Modifica los datos de un registro de historial existente (ej. corrección de diagnóstico o notas).
    boolean update(PetHistoryDTO record) throws SQLException;

    // Elimina logicamente un registro de historial clínico por su ID primario.
    boolean softDelete(int id) throws SQLException;

}
