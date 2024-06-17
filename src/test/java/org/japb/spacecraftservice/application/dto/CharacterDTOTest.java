package org.japb.spacecraftservice.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class CharacterDTOTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validateCharacterDTO_ValidDTO_ShouldNotViolateConstraints() {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(1L);
        characterDTO.setName("Luke Skywalker");
        characterDTO.setRole("Jedi Knight");
        characterDTO.setSpecies("Human");
        characterDTO.setGender("Male");
        characterDTO.setBirthDate(LocalDate.of(19, 5, 19)); // May 19th, 19 BBY
        characterDTO.setSpacecraftId(1L);

        Set<ConstraintViolation<CharacterDTO>> violations = validator.validate(characterDTO);

        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void validateCharacterDTO_InvalidDTO_ShouldViolateConstraints() {
        CharacterDTO characterDTO = new CharacterDTO();

        Set<ConstraintViolation<CharacterDTO>> violations = validator.validate(characterDTO);

        assertEquals(5, violations.size(), "Expected 5 validation errors");

        violations.forEach(violation -> {
            assertTrue(violation.getPropertyPath().toString().startsWith("name") ||
                            violation.getPropertyPath().toString().startsWith("role") ||
                            violation.getPropertyPath().toString().startsWith("species") ||
                            violation.getPropertyPath().toString().startsWith("gender") ||
                            violation.getPropertyPath().toString().startsWith("spacecraftId"),
                    "Expected validation errors on name, role, species, gender, and spacecraftId");
        });
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos objetos CharacterDTO con los mismos atributos
        CharacterDTO character1 = new CharacterDTO();
        character1.setId(1L);
        character1.setName("Luke Skywalker");
        character1.setRole("Jedi Knight");
        character1.setSpecies("Human");
        character1.setGender("Male");
        character1.setBirthDate(LocalDate.of(19, 5, 19)); // May 19th, 19 BBY
        character1.setSpacecraftId(1L);

        CharacterDTO character2 = new CharacterDTO();
        character2.setId(1L);
        character2.setName("Luke Skywalker");
        character2.setRole("Jedi Knight");
        character2.setSpecies("Human");
        character2.setGender("Male");
        character2.setBirthDate(LocalDate.of(19, 5, 19)); // May 19th, 19 BBY
        character2.setSpacecraftId(1L);

        // Verificar que los objetos sean iguales según equals
        assertTrue(character1.equals(character2));
        assertTrue(character2.equals(character1));

        // Verificar que tengan el mismo hash code
        assertEquals(character1.hashCode(), character2.hashCode());
    }
}