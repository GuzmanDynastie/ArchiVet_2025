package archivet.model;

import archivet.model.interfaces.PetDTO;
import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class CatDTO extends PetDTO {

    private Boolean isIndoor;

    public enum BreedEnum {
        MESTIZO("Criollo, Sin raza definida"),
        BOMBAY("Bombay"),
        AZUL_RUSO("Russian blue"),
        SIAMES("Siames"),
        PERSA("Persa"),
        MAINE_COON("Maine coon"),
        BENGALI("Bengali");

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
     * Constructor para generar un nuevo gato.
     *
     * @param ownerId
     * @param name
     * @param birthDate
     * @param sex
     * @param isSterilized
     * @param coatColor
     * @param speciesType
     * @param isIndoor
     * @param breed
     */
    public CatDTO(int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, Enum breed, Boolean isIndoor) {
        super(ownerId, name, birthDate, sex, isSterilized, coatColor, speciesType, breed);
        this.isIndoor = isIndoor;
    }

    /**
     * Constructor para obtener un gato de la base de datos.
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
     * @param isIndoor
     */
    public CatDTO(int petId, int ownerId, String name, LocalDate birthDate, SexEnum sex, boolean isSterilized, String coatColor, String speciesType, Enum breed, boolean isActive, Boolean isIndoor) {
        super(petId, ownerId, name, birthDate, sex, isSterilized, coatColor, speciesType, breed, isActive);
        this.isIndoor = isIndoor;
    }

    // Implementacion del metodo abstracto
    @Override
    public String getSpeciesName() {
        return "Cat";
    }

    @Override
    public String getDescriptionBreed(Enum breed) {
        return ((BreedEnum) breed).getDescription();
    }

    // Getters y Setters
    public Boolean getIsIndoor() {
        return isIndoor;
    }

    public void setIsIndoor(Boolean isIndoor) {
        this.isIndoor = isIndoor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CatDTO{");
        sb.append(super.toString());
        sb.append(", isIndoor=").append(isIndoor);
        sb.append('}');
        return sb.toString();
    }

}
