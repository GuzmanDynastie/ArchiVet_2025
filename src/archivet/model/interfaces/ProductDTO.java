package archivet.model.interfaces;

/**
 *
 * @author guzman-dynastie
 */
public abstract class ProductDTO {

    private int productId;
    private String name;
    private String description;
    private double unitPrice;
    private boolean is_active;

    /**
     * Constructor para generar un nuevo producto
     *
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public ProductDTO(String name, String description, double unitPrice, boolean is_active) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.is_active = is_active;
    }

    /**
     * Constructor para obtener un producto de la base de datos
     *
     * @param productId
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public ProductDTO(int productId, String name, String description, double unitPrice, boolean is_active) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.is_active = is_active;
    }

    // Metodo abstracto para diferencia el proposito (Venta o Registro Clinico)
    public abstract String getUsageType();

    // Getters y Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
