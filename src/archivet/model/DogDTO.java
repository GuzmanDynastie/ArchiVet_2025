package archivet.model;

import archivet.model.interfaces.PetDTO;
import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class DogDTO extends PetDTO {

    private String trainigLevel;

    public enum BreedEnum {
        MESTIZO("Criollo, Sin raza definida"),
        CHIHUAHUA("Chihuahua"),
        LABRADOR_RETRIEVER("Labrador retriever"),
        POODLE("Poodle"),
        PASTOR_ALEMAN("Pastor aleman"),
        PITBULL_TERRIER_AMERICANO("Pitbull terrier americano"),
        SCHNAUZER("Schnauzer"),
        FRENCH_POODLE("French poodle"),
        YORKSHIRE_TERRIER("Yorkshire terrier"),
        PUG("Pug");

        private final String description;

        // Constructor para asignar un valor de visualizacion.
        BreedEnum(String description) {
            this.description = description;
        }

        // Metodo que obtendra el valor que se almacenara o momstrara.
        public String getDescription() {
            return description;
        }

    }

    /**
     * Constructor para generar un nuevo perro.
     *
     * @param ownerId
     * @param name
     * @param birthDate
     * @param sex
     * @param isSterilized
     * @param coatColor
     * @param speciesType
     * @param breed
     * @param trainigLevel
     */
    public DogDTO(int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, Enum breed, String trainigLevel) {
        super(ownerId, name, birthDate, sex, isSterilized, coatColor, speciesType, breed);
        this.trainigLevel = trainigLevel;
    }

    /**
     * Constructor para obtener un perro de la base de datos.
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
     * @param trainigLevel
     */
    public DogDTO(int petId, int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, Enum breed, boolean isActive, String trainigLevel) {
        super(petId, ownerId, name, birthDate, sex, isSterilized, coatColor, speciesType, breed, isActive);
        this.trainigLevel = trainigLevel;
    }

    // Implementacion del metodo abstracto
    @Override
    public String getSpeciesName() {
        return "Dog";
    }

    @Override
    public String getDescriptionBreed(Enum breed) {
        return ((BreedEnum) breed).getDescription();
    }

    // Getters y Setters
    public String getTrainigLevel() {
        return trainigLevel;
    }

    public void setTrainigLevel(String trainigLevel) {
        this.trainigLevel = trainigLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DogDTO{");
        sb.append(super.toString());
        sb.append(", trainigLevel=").append(trainigLevel);
        sb.append('}');
        return sb.toString();
    }

}
