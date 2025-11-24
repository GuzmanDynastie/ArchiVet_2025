package archivet.model;

import archivet.model.interfaces.ProductDTO;
import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class VaccineRecordDTO extends ProductDTO {

    private String targetDisease;           // Ej: Rabia, Parvovirus, etc.
    private LocalDate lotExpirationDate;
    private String lotNumber;

    /**
     * Constructor para generar un nueva vacuna
     *
     * @param targetDisease
     * @param lotExpirationDate
     * @param lotNumber
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public VaccineRecordDTO(String targetDisease, LocalDate lotExpirationDate, String lotNumber, String name, String description, double unitPrice, boolean is_active) {
        super(name, description, unitPrice, is_active);
        this.targetDisease = targetDisease;
        this.lotExpirationDate = lotExpirationDate;
        this.lotNumber = lotNumber;
    }

    /**
     * Constructor para obtener una vacuna de la base de datos
     *
     * @param targetDisease
     * @param lotExpirationDate
     * @param lotNumber
     * @param productId
     * @param name
     * @param description
     * @param unitPrice
     * @param is_active
     */
    public VaccineRecordDTO(String targetDisease, LocalDate lotExpirationDate, String lotNumber, int productId, String name, String description, double unitPrice, boolean is_active) {
        super(productId, name, description, unitPrice, is_active);
        this.targetDisease = targetDisease;
        this.lotExpirationDate = lotExpirationDate;
        this.lotNumber = lotNumber;
    }

    @Override
    public String getUsageType() {
        return "CLINICAL_RECORD";
    }

    // Getters y Setters
    public String getTargetDisease() {
        return targetDisease;
    }

    public void setTargetDisease(String targetDisease) {
        this.targetDisease = targetDisease;
    }

    public LocalDate getLotExpirationDate() {
        return lotExpirationDate;
    }

    public void setLotExpirationDate(LocalDate lotExpirationDate) {
        this.lotExpirationDate = lotExpirationDate;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

}
