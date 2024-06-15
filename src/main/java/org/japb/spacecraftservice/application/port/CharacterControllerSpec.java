package org.japb.spacecraftservice.application.port;

import java.util.List;

import jakarta.validation.Valid;
import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



//@RequestMapping("/api/spacecrafts/{spacecraftId}/characters")
@RequestMapping("/api/characters")
public interface CharacterControllerSpec {
    @PostMapping("/spacecraft/{spacecraftId}")
    ResponseEntity<CharacterDTO> createCharacter(@PathVariable Long spacecraftId,
                                                 @Valid @RequestBody CharacterDTO characterDTO);

    @GetMapping("/spacecraft/{spacecraftId}")
    ResponseEntity<List<CharacterDTO>> getCharactersBySpacecraft(@PathVariable Long spacecraftId);

    @GetMapping
    ResponseEntity<List<CharacterDTO>> getAllCharacters();

    @GetMapping("/evictCache")
    ResponseEntity<String> evictCache();
}
