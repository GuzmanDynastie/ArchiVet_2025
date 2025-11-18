package archivet.dao;

import archivet.model.InventoryItemDTO;
import archivet.model.ProductDTO;
import archivet.model.VaccineRecordDTO;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author guzman-dynastie
 */
public interface IProductDAO {

    /**
     * Guarda un nuevo producto en la base de datos. Este método debe realizar
     * una transacción: insertar en la tabla PRODUCT y luego en la tabla
     * específica (INVENTORY_ITEM o VACCINE_RECORD).
     *
     * @param product El objeto ProductDTO (Inventario o Vacuna) a guardar.
     * @return true si la inserción fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean save(ProductDTO product) throws SQLException;

    /**
     * Busca un producto por su ID primario. El DAO debe leer el tipo de uso
     * ('INVENTORY_SALE' o 'CLINICAL_RECORD') para instanciar la subclase
     * correcta.
     *
     * @param id El ID (productId) del producto a buscar.
     * @return El objeto ProductDTO (subclase específica), o null si no se
     * encuentra.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    ProductDTO findById(int id) throws SQLException;

    /**
     * Obtiene una lista de todos los productos destinados a VENTA e INVENTARIO.
     * Se utiliza para mostrar el catálogo de artículos disponibles en la caja o
     * en el stock.
     *
     * @return Lista de InventoryItemDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<InventoryItemDTO> findAllInventoryItems() throws SQLException;

    /**
     * Obtiene una lista de todos los registros de Vacuna aplicados a una
     * mascota. Se utiliza para construir el historial de inmunización de la
     * mascota.
     *
     * @param petId El ID de la mascota cuyo historial de vacunas se desea
     * obtener.
     * @return Lista de VaccineRecordDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<VaccineRecordDTO> findVaccineRecordsByPetId(int petId) throws SQLException;

    /**
     * Modifica los datos de un producto existente. Este método debe actualizar
     * tanto la tabla PRODUCT como la tabla específica (e.g., actualizar el
     * stockQuantity si es un InventoryItem).
     *
     * @param product El objeto ProductDTO con los datos actualizados.
     * @return true si la actualización fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean update(ProductDTO product) throws SQLException;

    /**
     * Elimina un producto por su ID primario.
     *
     * @param id El ID (productId) del producto a eliminar.
     * @return true si la eliminación fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean delete(int id) throws SQLException;
}
