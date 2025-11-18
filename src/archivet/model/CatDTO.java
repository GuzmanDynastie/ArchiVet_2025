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
     * @param specieType
     * @param speciesType
     * @param isStirilazed
     * @param birthDate
     * @param ownerId
     * @param is_active
     */
    public CatDTO(String name, String coat_color, SexEnum sex, SpecieEnum specieType, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean is_active) {
        super(name, coat_color, sex, specieType, speciesType, isStirilazed, birthDate, ownerId, is_active);
    }

    /**
     * Constructor para obtener un gato de la base de datos
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
    public CatDTO(int petId, String name, String coat_color, SexEnum sex, SpecieEnum specieType, String speciesType, boolean isStirilazed, LocalDate birthDate, int ownerId, boolean is_active) {
        super(petId, name, coat_color, sex, specieType, speciesType, isStirilazed, birthDate, ownerId, is_active);
    }

    // Implementacion del metodo abstracto
    @Override
    public String getSpeciesName() {
        return "Cat";
    }

    // Getters y Setters
}
