package archivet.dao.interfaces;

import archivet.dao.ProductDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IProductDAO {

    // Guarda un nuevo producto en la base de datos.
    boolean save(ProductDAO product) throws SQLException;

    // Modifica los datos de un producto existente (tablas PRODUCT y la tabla específica).
    boolean update(ProductDAO product) throws SQLException;

    // Elimina logicamente un producto por su ID primario. Las tablas dependientes (VACCINE, INVENTORY_ITEM) deben ser eliminadas en cascada.
    boolean delete(int id) throws SQLException;

    // Busca un producto por su ID primario.
    ProductDAO findById(int id) throws SQLException;

    // Obtiene una lista de todos los items.
    List<ProductDAO> findAllInventoryItem() throws SQLException;

    // Obtiene una lista de todos las vacunas.
    List<ProductDAO> findAllVaccine() throws SQLException;

}
