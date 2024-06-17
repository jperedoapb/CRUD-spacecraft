package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.CharacterRepository;
import org.japb.spacecraftservice.domain.repository.SpacecraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private SpacecraftRepository spacecraftRepository;

    @InjectMocks
    private CharacterServiceImpl characterService;

    private Spacecraft spacecraft;
    private SpaceCharacter character1;
    private SpaceCharacter character2;

    @BeforeEach
    void setUp() {
        spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("Test Spacecraft");

        character1 = new SpaceCharacter();
        character1.setId(1L);
        character1.setName("Character 1");
        character1.setSpacecraft(spacecraft);

        character2 = new SpaceCharacter();
        character2.setId(2L);
        character2.setName("Character 2");
        character2.setSpacecraft(spacecraft);
    }

    @Test
    void testCreateCharacter() {
        // Mock repository behavior
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));
        when(characterRepository.save(any(SpaceCharacter.class))).thenReturn(character1);

        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName("New Character");

        CharacterDTO createdCharacter = characterService.createCharacter(1L, characterDTO);

        assertNotNull(createdCharacter);
        //assertEquals(characterDTO.getName(), createdCharacter.getName());
        assertEquals(spacecraft.getSpaceId(), createdCharacter.getSpacecraftId());

        verify(spacecraftRepository, times(1)).findById(1L);
        verify(characterRepository, times(1)).save(any(SpaceCharacter.class));
    }

    @Test
    void testGetCharactersBySpacecraft() {
        // Mock repository behavior
        when(characterRepository.findBySpacecraftSpaceId(1L)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterDTO> characters = characterService.getCharactersBySpacecraft(1L);

        assertNotNull(characters);
        assertEquals(2, characters.size());

        CharacterDTO dto1 = characters.get(0);
        assertEquals(character1.getName(), dto1.getName());
        assertEquals(spacecraft.getSpaceId(), dto1.getSpacecraftId());

        CharacterDTO dto2 = characters.get(1);
        assertEquals(character2.getName(), dto2.getName());
        assertEquals(spacecraft.getSpaceId(), dto2.getSpacecraftId());

        verify(characterRepository, times(1)).findBySpacecraftSpaceId(1L);
    }

    @Test
    void testGetCharactersBySpacecraft_NoCharactersFound() {
        // Mock repository behavior
        when(characterRepository.findBySpacecraftSpaceId(1L)).thenReturn(Arrays.asList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            characterService.getCharactersBySpacecraft(1L);
        });

        String expectedMessage = "Characters not found for spacecraft ID 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(characterRepository, times(1)).findBySpacecraftSpaceId(1L);
    }

    @Test
    void testGetAllCharacters() {
        // Mock repository behavior
        when(characterRepository.findAll()).thenReturn(Arrays.asList(character1, character2));

        List<CharacterDTO> characters = characterService.getAllCharacters();

        assertNotNull(characters);
        assertEquals(2, characters.size());

        CharacterDTO dto1 = characters.get(0);
        assertEquals(character1.getName(), dto1.getName());
        assertEquals(spacecraft.getSpaceId(), dto1.getSpacecraftId());

        CharacterDTO dto2 = characters.get(1);
        assertEquals(character2.getName(), dto2.getName());
        assertEquals(spacecraft.getSpaceId(), dto2.getSpacecraftId());

        verify(characterRepository, times(1)).findAll();
    }


}