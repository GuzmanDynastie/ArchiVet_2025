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
    private String coat_color;
    private SexEnum sex;
    private SpecieEnum specieType;
    private String speciesType;
    private boolean isStirilazed;
    private LocalDate birthDate;
    private int ownerId;
    private boolean is_active;

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
     * @param coat_color
     * @param sex
     * @param specieType
     * @param speciesType
     * @param isStirilazed
     * @param birthDate
     * @param ownerId
     * @param is_active
     */
    public PetDTO(String name, String coat_color, SexEnum sex, SpecieEnum specieType, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean is_active) {
        this.name = name;
        this.coat_color = coat_color;
        this.sex = sex;
        this.specieType = specieType;
        this.speciesType = speciesType;
        this.isStirilazed = isStirilazed;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.is_active = is_active;
    }

    /**
     * Constructor para obtener una mascota de la base de datos
     *
     * @param petId
     * @param name
     * @param coat_color
     * @param sex
     * @param specieType
     * @param speciesType
     * @param isStirilazed
     * @param birthDate
     * @param ownerId
     * @param is_active
     */
    public PetDTO(int petId, String name, String coat_color, SexEnum sex, SpecieEnum specieType, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean is_active) {
        this.petId = petId;
        this.name = name;
        this.coat_color = coat_color;
        this.sex = sex;
        this.specieType = specieType;
        this.speciesType = speciesType;
        this.isStirilazed = isStirilazed;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.is_active = is_active;
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

    public String getCoat_color() {
        return coat_color;
    }

    public void setCoat_color(String coat_color) {
        this.coat_color = coat_color;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public SpecieEnum getSpecieType() {
        return specieType;
    }

    public void setSpecieType(SpecieEnum specieType) {
        this.specieType = specieType;
    }

    public String getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(String speciesType) {
        this.speciesType = speciesType;
    }

    public boolean isIsStirilazed() {
        return isStirilazed;
    }

    public void setIsStirilazed(boolean isStirilazed) {
        this.isStirilazed = isStirilazed;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

}
