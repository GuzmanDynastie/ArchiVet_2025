package archivet.model;

import java.time.LocalDateTime;

/**
 *
 * @author guzman-dynastie
 */
public class SaleDTO {

    private int saleId;
    private int vetDoctorId;
    private int ownerId;
    private int petId;
    private LocalDateTime dateTime;
    private double totalAmount;
    private double amountPaid;
    private double changeAmount;
    private String paymentMethod;       // Efectivo
    private String status;              // Completo
    
    /**
     * Constructor para generar una nueva venta
     * 
     * @param vetDoctorId
     * @param ownerId
     * @param petId
     * @param dateTime
     * @param totalAmount
     * @param amountPaid
     * @param changeAmount
     * @param paymentMethod
     * @param status 
     */
    public SaleDTO(int vetDoctorId, int ownerId, int petId, LocalDateTime dateTime, double totalAmount, double amountPaid, double changeAmount, String paymentMethod, String status) {
        this.vetDoctorId = vetDoctorId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.changeAmount = changeAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    /**
     * Constructor para obtener una venta de la base de datos
     * 
     * @param saleId
     * @param vetDoctorId
     * @param ownerId
     * @param petId
     * @param dateTime
     * @param totalAmount
     * @param amountPaid
     * @param changeAmount
     * @param paymentMethod
     * @param status 
     */
    public SaleDTO(int saleId, int vetDoctorId, int ownerId, int petId, LocalDateTime dateTime, double totalAmount, double amountPaid, double changeAmount, String paymentMethod, String status) {
        this.saleId = saleId;
        this.vetDoctorId = vetDoctorId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.changeAmount = changeAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Getters y Setters
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getVetDoctorId() {
        return vetDoctorId;
    }

    public void setVetDoctorId(int vetDoctorId) {
        this.vetDoctorId = vetDoctorId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
