package archivet.model;

import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class PetHistoryDTO {

    private int historyId;
    private int petId;
    private int vetDoctorId;
    private LocalDate date;
    private String diagnosis;
    private String treatment;
    private String notes;
    private boolean requiresFollowUp;

    /**
     * Constructor para generar un historial a una mascota
     *
     * @param petId
     * @param vetDoctorId
     * @param date
     * @param diagnosis
     * @param treatment
     * @param notes
     * @param requiresFollowUp
     */
    public PetHistoryDTO(int petId, int vetDoctorId, LocalDate date, String diagnosis, String treatment, String notes, boolean requiresFollowUp) {
        this.petId = petId;
        this.vetDoctorId = vetDoctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.requiresFollowUp = requiresFollowUp;
    }

    /**
     * Constructor para obtener el historial de una mascota de la base de datos
     *
     * @param historyId
     * @param petId
     * @param vetDoctorId
     * @param date
     * @param diagnosis
     * @param treatment
     * @param notes
     * @param requiresFollowUp
     */
    public PetHistoryDTO(int historyId, int petId, int vetDoctorId, LocalDate date, String diagnosis, String treatment, String notes, boolean requiresFollowUp) {
        this.historyId = historyId;
        this.petId = petId;
        this.vetDoctorId = vetDoctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.requiresFollowUp = requiresFollowUp;
    }

    // Getters y Setters
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVetDoctorId() {
        return vetDoctorId;
    }

    public void setVetDoctorId(int vetDoctorId) {
        this.vetDoctorId = vetDoctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRequiresFollowUp() {
        return requiresFollowUp;
    }

    public void setRequiresFollowUp(boolean requiresFollowUp) {
        this.requiresFollowUp = requiresFollowUp;
    }

}
