package archivet.model;

/**
 *
 * @author guzman-dynastie
 */
// Clase abstracta que evita que se creen instancias genericas
public abstract class UserDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private SexEnum sex;
    private boolean isActive;

    public enum SexEnum {
        MALE("Hombre"),
        FEMALE("Mujer");

        private final String displayValue;

        // Constructor para asignar un valor de visualizacion
        SexEnum(String displayVale) {
            this.displayValue = displayVale;
        }

        // Metodo para obtener el valor que se mostrara o almacenara
        public String getDisplayValue() {
            return displayValue;
        }
    }

    /**
     * Constructor para generar un nuevo usuario
     */
    public UserDTO(String firstName, String lastName, String email, String passwordHash, String phoneNumber, SexEnum sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
    
    /**
     * Constructor para obtener usuario de la base de datos
     */
    public UserDTO(int userId, String firstName, String lastName, String email, String passwordHash, String phoneNumber, SexEnum sex, boolean isActive) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.isActive = isActive;
    }

    // Metodo abstracto para polimorfismo que define el rol
    public abstract String getRoleName();

    // Getters y Setters (Encapsulamiento y camelCase
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(userId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", passwordHash=").append(passwordHash);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", sex=").append(sex.getDisplayValue());
        sb.append(", isActive=").append(isActive);
        return sb.toString();
    }

}
