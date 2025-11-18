package archivet.dao;

import archivet.model.InventoryItemDTO;
import archivet.model.SaleDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface ISaleDAO {

    /**
     * Guarda la cabecera de la venta (SaleDTO) y todos sus artículos asociados
     * (SaleItem) en una transacción atómica. Este proceso debe: 1) Insertar en
     * la tabla SALE, 2) Insertar en SALE_ITEM, y 3) Actualizar el
     * stock_quantity en la tabla INVENTORY_ITEM.
     *
     * @param sale El objeto SaleDTO con los datos de la cabecera de la venta.
     * @return El ID de la venta (saleId) generado si la transacción es exitosa.
     * @throws SQLException Si ocurre un error, se debe hacer ROLLBACK.
     */
    int saveSale(SaleDTO sale, List<InventoryItemDTO> soldItems) throws SQLException;

    /**
     * Busca la cabecera de una venta por su ID primario.
     *
     * @param id El ID (saleId) de la venta.
     * @return El objeto SaleDTO encontrado, o null si no existe.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    SaleDTO findById(int id) throws SQLException;

    /**
     * Obtiene todos los artículos (medicamentos, desparasitantes) vendidos en
     * una transacción específica. Se utiliza para reconstruir el detalle de una
     * factura.
     *
     * @param saleId El ID de la venta de la cual se desean obtener los ítems.
     * @return Lista de InventoryItemDTOs vendidos.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<InventoryItemDTO> findItemsBySaleId(int saleId) throws SQLException;

    /**
     * Obtiene el historial de ventas realizadas por un médico veterinario
     * específico.
     *
     * @param vetDoctorId El ID del doctor (VetDoctorDTO) que realizó las
     * ventas.
     * @return Lista de SaleDTOs asociados al doctor.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<SaleDTO> findByVetDoctorId(int vetDoctorId) throws SQLException;

    /**
     * Obtiene una lista de todas las ventas registradas en el sistema.
     *
     * @return Lista de todos los SaleDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<SaleDTO> findAll() throws SQLException;

    /**
     * Obtiene todos los artículos de inventario vendidos asociados al historial
     * de una mascota. La búsqueda se realiza a través del campo pet_id en la
     * cabecera de la venta (SaleDTO).
     *
     * @param petId El ID de la mascota.
     * @return Lista de InventoryItemDTOs que fueron comprados para esta
     * mascota.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<InventoryItemDTO> findItemsByPetId(int petId) throws SQLException;

}
