package archivet.service;

import archivet.model.InventoryItemDTO;
import archivet.model.ProductDTO;
import archivet.model.VaccineRecordDTO;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author guzman-dynastie
 */
public interface IProductService {

    // Guarda un nuevo producto en la base de datos.
    boolean save(ProductDTO product) throws SQLException;

    // Busca un producto por su ID primario.
    ProductDTO findById(int id) throws SQLException;

    // Obtiene una lista de todos los productos destinados a VENTA e INVENTARIO.
    List<InventoryItemDTO> findAllInventoryItems() throws SQLException;

    // Obtiene una lista de todos los registros de Vacuna aplicados a una mascota.
    List<VaccineRecordDTO> findVaccineRecordsByPetId(int petId) throws SQLException;

    // Modifica los datos de un producto existente.
    boolean update(ProductDTO product) throws SQLException;

    // Elimina logicamente un producto por su ID primario.
    boolean softDelete(int id) throws SQLException;
    
}
