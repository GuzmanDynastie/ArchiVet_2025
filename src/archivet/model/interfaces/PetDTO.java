package archivet.model.interfaces;

import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */

// Clase abstracta que evita que se creen instancias genericas
// 1. Se declro la clase abstracta como generica con <T>
// Donde T sera el tipo de BreedEnum especifico (DogDTO.BreedEnum o CatDTO.BreedEnum)
public abstract class PetDTO<T extends Enum<T>> {

    private int petId;
    private int ownerId;
    private String name;
    private LocalDate birthDate;
    private SexEnum sex;
    private boolean isSterilized;
    private String coatColor;
    private String speciesType;
    private T breed;
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

    /**
     * Constructor para generar una nueva mascota.
     *
     * @param ownerId
     * @param name
     * @param birthDate
     * @param sex
     * @param isSterilized
     * @param coatColor
     * @param speciesType
     * @param breed
     */
    public PetDTO(int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, T breed) {
        this.ownerId = ownerId;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.isSterilized = isSterilized;
        this.coatColor = coatColor;
        this.speciesType = speciesType;
        this.breed = breed;
    }

    /**
     * Constructor para obtener una mascota de la base de datos.
     *
     * @param petId
     * @param ownerId
     * @param name
     * @param birthDate
     * @param sex
     * @param isSterilized
     * @param coatColor
     * @param speciesType
     * @param breed
     * @param isActive
     */
    public PetDTO(int petId, int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, T breed, boolean isActive) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.isSterilized = isSterilized;
        this.coatColor = coatColor;
        this.speciesType = speciesType;
        this.breed = breed;
        this.isActive = isActive;
    }

    // Metodo abstracto para polimorfismo que define la especie
    public abstract String getSpeciesName();
    
    public abstract String getDescriptionBreed(T breed);

    // Getters y Setters
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public boolean getIsSterilized() {
        return isSterilized;
    }

    public void setIsSterilized(boolean isSterilized) {
        this.isSterilized = isSterilized;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(String speciesType) {
        this.speciesType = speciesType;
    }

    public T getBreed() {
        return breed;
    }

    public void setBreed(T breed) {
        this.breed = breed;
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
        sb.append(", ownerId=").append(ownerId);
        sb.append(", name=").append(name);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", sex=").append(sex);
        sb.append(", isSterilized=").append(isSterilized);
        sb.append(", coatColor=").append(coatColor);
        sb.append(", speciesType=").append(speciesType);
        sb.append(", breed=").append(getDescriptionBreed(breed));
        sb.append(", isActive=").append(isActive);
        return sb.toString();
    }

}
