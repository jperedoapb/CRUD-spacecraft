package org.japb.spacecraftservice.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
public class SpaceCharacterTest {

    @Test
    public void testEqualsAndHashCode() {
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);

        SpaceCharacter character1 = new SpaceCharacter();
        character1.setId(1L);
        character1.setName("Han Solo");
        character1.setRole("Pilot");
        character1.setSpecies("Human");
        character1.setGender("Male");
        character1.setBirthDate(LocalDate.of(1977, 5, 25));
        character1.setSpacecraft(spacecraft);

        SpaceCharacter character2 = new SpaceCharacter();
        character2.setId(1L);
        character2.setName("Han Solo");
        character2.setRole("Pilot");
        character2.setSpecies("Human");
        character2.setGender("Male");
        character2.setBirthDate(LocalDate.of(1977, 5, 25));
        character2.setSpacecraft(spacecraft);

        assertThat(character1).isEqualTo(character2);
        assertThat(character1.hashCode()).isEqualTo(character2.hashCode());
    }

    @Test
    public void testSerializationDeserialization() {
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);

        SpaceCharacter originalCharacter = new SpaceCharacter();
        originalCharacter.setId(1L);
        originalCharacter.setName("Leia Organa");
        originalCharacter.setRole("General");
        originalCharacter.setSpecies("Human");
        originalCharacter.setGender("Female");
        originalCharacter.setBirthDate(LocalDate.of(1977, 10, 21));
        originalCharacter.setSpacecraft(spacecraft);

        // Serialize the object
        byte[] serializedCharacter = SerializationUtils.serialize(originalCharacter);

        // Deserialize the object
        SpaceCharacter deserializedCharacter = (SpaceCharacter) SerializationUtils.deserialize(serializedCharacter);

        assertThat(deserializedCharacter).isEqualTo(originalCharacter);
    }
}
