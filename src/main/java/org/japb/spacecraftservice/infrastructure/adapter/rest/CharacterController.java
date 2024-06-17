package org.japb.spacecraftservice.infrastructure.adapter.rest;

import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.application.port.CharacterControllerSpec;
import org.japb.spacecraftservice.application.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
class CharacterController implements CharacterControllerSpec {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    public ResponseEntity<CharacterDTO> createCharacter(Long spacecraftId, CharacterDTO characterDTO) {
        CharacterDTO createdCharacter = characterService.createCharacter(spacecraftId, characterDTO);
        return new ResponseEntity<>(createdCharacter, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CharacterDTO>> getCharactersBySpacecraft(Long spacecraftId) {
        if (spacecraftId < 0) {
            throw new IllegalArgumentException("Character ID cannot be negative");
        }
        List<CharacterDTO> characters = characterService.getCharactersBySpacecraft(spacecraftId);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> evictCache() {
        characterService.clearCache();
        return new ResponseEntity<>("Cache cleared", HttpStatus.OK);
    }
}
