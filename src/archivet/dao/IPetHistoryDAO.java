package archivet.dao;

import archivet.model.PetHistoryDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetHistoryDAO {

    /**
     * Guarda un nuevo registro de historial clínico en la base de datos.
     *
     * @param record El objeto PetHistoryDTO a guardar (sin ID).
     * @return true si la inserción fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean save(PetHistoryDTO record) throws SQLException;

    /**
     * Busca un registro de historial clínico individual por su ID primario. *
     *
     * * @param id El ID (historyId) del registro específico a buscar.
     * @return El objeto PetHistoryDTO encontrado, o null si no existe.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    PetHistoryDTO findById(int id) throws SQLException;

    /**
     * Recupera todos los registros de historial clínico asociados a una
     * mascota. La lista debe estar ordenada por fecha descendente.
     *
     * @param petId El ID de la mascota cuyo historial se desea obtener.
     * @return Lista de todos los PetHistoryDTOs de esa mascota.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<PetHistoryDTO> findByPetId(int petId) throws SQLException;

    /**
     * Recupera una lista de todos los registros de historial clínico en el
     * sistema. Este método se utiliza típicamente para fines administrativos o
     * de reporte.
     *
     * @return Lista de todos los PetHistoryDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<PetHistoryDTO> findAll() throws SQLException;

    /**
     * Modifica los datos de un registro de historial existente (ej. corrección
     * de diagnóstico o notas). El objeto PetHistoryDTO debe contener un ID
     * válido.
     *
     * @param record El objeto PetHistoryDTO con los datos actualizados.
     * @return true si la actualización fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean update(PetHistoryDTO record) throws SQLException;

    /**
     * Elimina un registro de historial clínico por su ID primario.
     *
     * @param id El ID (historyId) del registro a eliminar.
     * @return true si la eliminación fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean delete(int id) throws SQLException;

}
