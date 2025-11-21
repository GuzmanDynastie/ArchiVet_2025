package archivet.model;

import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
// Clase abstracta que evita que se creen instancias genericas
public abstract class PetDTO {

    private int petId;
    private String name;
    private String coatColor;
    private SexEnum sex;
    private SpecieEnum speciesType;
    private boolean isSterilized;
    private LocalDate birthDate;
    private int ownerId;
    private boolean isActive;

    public enum SexEnum {
        MALE("Macho"),
        FEMALE("Hembra"),
        UNKNOWN("Desconocido");

        private final String description;

        // Constructor para asignar un valor de visualizacion
        SexEnum(String description) {
            this.description = description;
        }

        // Metodo para obtener el valor que se mostrara o almacenara
        public String getDescription() {
            return description;
        }
    }

    public enum SpecieEnum {
        DOG("Perro"),
        CAT("Gato");

        private final String description;

        // Constructor para asignar un valor de visualizacion
        SpecieEnum(String description) {
            this.description = description;
        }

        // Metodo para obtener el valor que se mostrara o almacenara
        public String getDescription() {
            return description;
        }
    }

    /**
     * Constructor para generar una nueva mascota
     *
     * @param name
     * @param coatColor
     * @param sex
     * @param speciesType
     * @param isSterilized
     * @param birthDate
     * @param ownerId
     */
    public PetDTO(String name, String coatColor, SexEnum sex, SpecieEnum speciesType, boolean isSterilized, LocalDate birthDate, int ownerId) {
        this.name = name;
        this.coatColor = coatColor;
        this.sex = sex;
        this.speciesType = speciesType;
        this.isSterilized = isSterilized;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
    }

    /**
     * Constructor para obtener una mascota de la base de datos
     *
     * @param petId
     * @param name
     * @param coatColor
     * @param sex
     * @param speciesType
     * @param isSterilized
     * @param birthDate
     * @param ownerId
     * @param isActive
     */
    public PetDTO(int petId, String name, String coatColor, SexEnum sex, SpecieEnum speciesType, boolean isSterilized, LocalDate birthDate, int ownerId, boolean isActive) {
        this.petId = petId;
        this.name = name;
        this.coatColor = coatColor;
        this.sex = sex;
        this.speciesType = speciesType;
        this.isSterilized = isSterilized;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.isActive = isActive;
    }

    // Metodo abstracto para polimorfismo que define la especie
    public abstract String getSpeciesName();

    // Getters y Setters
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public SpecieEnum getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(SpecieEnum speciesType) {
        this.speciesType = speciesType;
    }

    public boolean getIsSterilized() {
        return isSterilized;
    }

    public void setIsSterilized(boolean isSterilized) {
        this.isSterilized = isSterilized;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("petId=").append(petId);
        sb.append(", name=").append(name);
        sb.append(", coatColor=").append(coatColor);
        sb.append(", sex=").append(sex);
        sb.append(", speciesType=").append(speciesType);
        sb.append(", isSterilized=").append(isSterilized);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", isActive=").append(isActive);
        return sb.toString();
    }

}
