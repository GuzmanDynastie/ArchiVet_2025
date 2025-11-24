package archivet.model;

import archivet.model.interfaces.ProductDTO;
import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class InventoryItemDTO extends ProductDTO {

    private int stockQuantity;
    private ItemCategory category;
    private LocalDate expirationDate;

    private enum ItemCategory {
        MEDICINE("Antibioticos, Analgesicos"),
        DEWORMER("Desparacitantes"),
        SUPPLEMNET("Vitaminas"),
        OTHER("Otros");

        private final String description;

        ItemCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Constructor para generar un nuevo InventoryItem
     *
     * @param stockQuantity
     * @param category
     * @param expirationDate
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public InventoryItemDTO(int stockQuantity, ItemCategory category, LocalDate expirationDate, String name, String description, double unitPrice, boolean is_active) {
        super(name, description, unitPrice, is_active);
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.expirationDate = expirationDate;
    }

    /**
     * Constructor para obtener un InventoryItem de la base de datos
     *
     * @param stockQuantity
     * @param category
     * @param expirationDate
     * @param productId
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public InventoryItemDTO(int stockQuantity, ItemCategory category, LocalDate expirationDate, int productId, String name, String description, double unitPrice, boolean is_active) {
        super(productId, name, description, unitPrice, is_active);
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.expirationDate = expirationDate;
    }

    @Override
    public String getUsageType() {
        return "INVENTORY_SALE";
    }

    // Getters y Setters
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

}
