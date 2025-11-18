package archivet.model;

import archivet.util.PasswordUtil;

/**
 *
 * @author guzman-dynastie
 */
public class VetDoctorDTO extends UserDTO {

    private String licenseNumber;
    private String specialization;
    private String shiftSchedule; // Ej: "9:00 - 17:00 Lunes a Viernes"

    /**
     * Constructor para generar un nuevo veterinario
     *
     * @param licenseNumber
     * @param specialization
     * @param shiftSchedule
     * @param firstName
     * @param lastName
     * @param email
     * @param passwordHash
     * @param phoneNumber
     * @param sex
     */
    public VetDoctorDTO(String licenseNumber, String specialization, String shiftSchedule, String firstName, String lastName, String email, String plainPassword, String phoneNumber, SexEnum sex) {
        super(firstName, lastName, email, PasswordUtil.hashPassword(plainPassword), phoneNumber, sex);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
        this.shiftSchedule = shiftSchedule;
    }

    /**
     * Constructor para obtener un veterinario de la base de datos
     *
     * @param licenseNumber
     * @param specialization
     * @param shiftSchedule
     * @param userId
     * @param firstName
     * @param lastName
     * @param email
     * @param plainPassword
     * @param phoneNumber
     * @param sex
     * @param isActive
     */
    public VetDoctorDTO(String licenseNumber, String specialization, String shiftSchedule, int userId, String firstName, String lastName, String email, String plainPassword, String phoneNumber, SexEnum sex, boolean isActive) {
        super(userId, firstName, lastName, email, plainPassword, phoneNumber, sex, isActive);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
        this.shiftSchedule = shiftSchedule;
    }

    // Implementacion del metodo abstracto
    @Override
    public String getRoleName() {
        return "VetDoctor";
    }

    // Getters y Setters
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getShiftSchedule() {
        return shiftSchedule;
    }

    public void setShiftSchedule(String shiftSchedule) {
        this.shiftSchedule = shiftSchedule;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VetDoctorDTO{");
        sb.append("licenseNumber=").append(licenseNumber);
        sb.append(", specialization=").append(specialization);
        sb.append(", shiftSchedule=").append(shiftSchedule);
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }

}
