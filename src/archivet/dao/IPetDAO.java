package archivet.dao;

import archivet.model.PetDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IPetDAO {

    /**
     * Guarda una nueva mascota en la base de datos. Este método debe realizar
     * una transacción: insertar en la tabla PET y luego en la tabla específica
     * (DOG o CAT).
     *
     * @param pet El objeto PetDTO (DogDTO o CatDTO) a guardar.
     * @return true si la inserción fue exitosa.
     * @throws SQLException Si ocurre un error de base de datos (ej. ownerId
     * inválido).
     */
    boolean save(PetDTO pet) throws SQLException;

    /**
     * Busca una mascota por su ID. El DAO debe leer el tipo de especie de la DB
     * para instanciar el objeto correcto (DogDTO o CatDTO) y devolverlo como
     * PetDTO (Polimorfismo).
     *
     * @param id El ID (petId) de la mascota a buscar.
     * @return El objeto PetDTO, o null si no se encuentra.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    PetDTO findById(int id) throws SQLException;

    /**
     * Busca todas las mascotas asociadas a un dueño específico.
     *
     * @param ownerId El ID del dueño (OwnerDTO) de las mascotas.
     * @return Lista de PetDTOs (puede contener DogDTOs y CatDTOs) del dueño.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<PetDTO> findByOwnerId(int ownerId) throws SQLException;

    /**
     * Modifica los datos de una mascota existente. Este método debe actualizar
     * tanto la tabla PET (datos comunes) como la tabla específica (DOG o CAT).
     *
     * @param pet El objeto PetDTO con los datos actualizados.
     * @return true si la actualización fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean update(PetDTO pet) throws SQLException;

    /**
     * Elimina una mascota y todos sus registros asociados. La eliminación en
     * cascada debe ser gestionada en las tablas (DOG, CAT) y PET.
     *
     * @param id El ID (petId) de la mascota a eliminar.
     * @return true si la eliminación fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean delete(int id) throws SQLException;

    /**
     * Obtiene una lista de todas las mascotas registradas en el sistema.
     *
     * @return Lista de PetDTOs de todas las mascotas.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<PetDTO> findAll() throws SQLException;
}
