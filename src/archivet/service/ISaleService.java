package archivet.service;

import archivet.model.InventoryItemDTO;
import archivet.model.SaleDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface ISaleService {

    // Guarda la cabecera de la venta (SaleDTO) y todos sus artículos asociados (SaleItem) en una transacción atómica.
    int saveSale(SaleDTO sale, List<InventoryItemDTO> soldItems) throws SQLException;

    // Busca la cabecera de una venta por su ID primario.
    SaleDTO findById(int id) throws SQLException;

    // Obtiene todos los artículos (medicamentos, desparasitantes) vendidos en una transacción específica.
    List<InventoryItemDTO> findItemsBySaleId(int saleId) throws SQLException;

    // Obtiene el historial de ventas realizadas por un médico veterinario específico.
    List<SaleDTO> findByVetDoctorId(int vetDoctorId) throws SQLException;

    // Obtiene una lista de todas las ventas registradas en el sistema.
    List<SaleDTO> findAll() throws SQLException;

    // Obtiene todos los artículos de inventario vendidos asociados al historial de una mascota.
    List<InventoryItemDTO> findItemsByPetId(int petId) throws SQLException;

}
