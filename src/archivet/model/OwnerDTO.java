package archivet.model;

import archivet.util.PasswordUtil;

/**
 *
 * @author guzman-dynastie
 */
public class OwnerDTO extends UserDTO {

    final static String FAKE_PASSWORD_HASH = PasswordUtil.hashPassword("HASH_NO_ACCESS_OWNER_ID");
    private String billingAddress;

    /**
     * Constructor para generar un nuevo propietario
     *
     * @param billingAddress
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param sex
     */
    public OwnerDTO(String billingAddress, String firstName, String lastName, String email, String phoneNumber, SexEnum sex) {
        super(firstName, lastName, email, FAKE_PASSWORD_HASH, phoneNumber, sex);
        this.billingAddress = billingAddress;
    }

    /**
     * Constructor para obtener un propietario de la base de datos
     *
     * @param billingAddress
     * @param userId
     * @param firstName
     * @param lastName
     * @param email
     * @param passwordHash
     * @param phoneNumber
     * @param sex
     * @param is_active
     */
    public OwnerDTO(String billingAddress, int userId, String firstName, String lastName, String email, String passwordHash, String phoneNumber, SexEnum sex, boolean is_active) {
        super(userId, firstName, lastName, email, passwordHash, phoneNumber, sex, is_active);
        this.billingAddress = billingAddress;
    }

    // Implementacion del metodo abstracto
    @Override
    public String getRoleName() {
        return "Owner";
    }

    // Getters y Setters
    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();  
        sb.append("OwnerDTO{");
        sb.append("billingAddress=").append(billingAddress);
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
    
    

}
