package archivet.model;

import java.time.LocalDateTime;

/**
 *
 * @author guzman-dynastie
 */
public class AppointmentDTO {

    private int appointmentId;
    private int petId;
    private int vetDoctorId;
    private LocalDateTime dataTime;
    private String reason;
    private StatusEnum status;
    private String notes;

    public enum StatusEnum {
        SCHEDULED("Agendada"),
        COMPLETED("Completada"),
        CANCELLED("Cancelada");

        private final String description;

        private StatusEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Constructor para generar una nueva cita
     *
     * @param petId
     * @param vetDoctorId
     * @param dataTime
     * @param reason
     * @param status
     * @param notes
     */
    public AppointmentDTO(int petId, int vetDoctorId, LocalDateTime dataTime, String reason, StatusEnum status, String notes) {
        this.petId = petId;
        this.vetDoctorId = vetDoctorId;
        this.dataTime = dataTime;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
    }

    /**
     * Constructor para obtener una cita de la base de datos
     *
     * @param appointmentId
     * @param petId
     * @param vetDoctorId
     * @param dataTime
     * @param reason
     * @param status
     * @param notes
     */
    public AppointmentDTO(int appointmentId, int petId, int vetDoctorId, LocalDateTime dataTime, String reason, StatusEnum status, String notes) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.vetDoctorId = vetDoctorId;
        this.dataTime = dataTime;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
    }

    // Getters y Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppointmentDTO{");
        sb.append("appointmentId=").append(appointmentId);
        sb.append(", petId=").append(petId);
        sb.append(", vetDoctorId=").append(vetDoctorId);
        sb.append(", dataTime=").append(dataTime);
        sb.append(", reason=").append(reason);
        sb.append(", status=").append(status);
        sb.append(", notes=").append(notes);
        sb.append('}');
        return sb.toString();
    }

}
