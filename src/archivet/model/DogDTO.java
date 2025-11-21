package archivet.model;

import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class DogDTO extends PetDTO {

    /**
     * Constructor para generar un nuevo perro
     *
     * @param name
     * @param coatColor
     * @param sex
     * @param speciesType
     * @param isStirilazed
     * @param birthDate
     * @param ownerId
     */
    public DogDTO(String name, String coatColor, SexEnum sex, SpecieEnum speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId) {
        super(name, coatColor, sex, speciesType, isStirilazed, birthDate, ownerId);
    }

    /**
     * Constructor para obtener un perro de la base de datos
     *
     * @param petId
     * @param name
     * @param coat_color
     * @param sex
     * @param speciesType
     * @param isStirilazed
     * @param birthDate
     * @param ownerId
     * @param isActive
     */
    public DogDTO(int petId, String name, String coat_color, SexEnum sex, SpecieEnum speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean isActive) {
        super(petId, name, coat_color, sex, speciesType, isStirilazed, birthDate, ownerId, isActive);
    }

    // Implementacion del metodo abstracto
    @Override
    public String getSpeciesName() {
        return "Dog";
    }

    // Getters y Setters

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DogDTO{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
    
    
}
