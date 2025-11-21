package archivet.model;

import java.time.LocalDate;

/**
 *
 * @author guzman-dynastie
 */
public class CatDTO extends PetDTO {

    /**
     * Constructor para generar un nuevo gato
     *
     * @param name
     * @param coat_color
     * @param sex
     * @param speciesType
     * @param isSterilized
     * @param birthDate
     * @param ownerId
     */
    public CatDTO(String name, String coat_color, SexEnum sex, SpecieEnum speciesType, boolean isSterilized, LocalDate birthDate, int ownerId) {
        super(name, coat_color, sex, speciesType, isSterilized, birthDate, ownerId);
    }

    /**
     * Constructor para obtener un gato de la base de datos
     *
     * @param petId
     * @param name
     * @param coat_color
     * @param sex
     * @param speciesType
     * @param isSterilized
     * @param birthDate
     * @param ownerId
     * @param isActive
     */
    public CatDTO(int petId, String name, String coat_color, SexEnum sex, SpecieEnum speciesType, boolean isSterilized, LocalDate birthDate, int ownerId, boolean isActive) {
        super(petId, name, coat_color, sex, speciesType, isSterilized, birthDate, ownerId, isActive);
    }

    // Implementacion del metodo abstracto
    @Override
    public String getSpeciesName() {
        return "Cat";
    }

    // Getters y Setters

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CatDTO{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
    
    
}
