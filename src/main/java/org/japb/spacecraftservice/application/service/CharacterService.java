package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {
    CharacterDTO createCharacter(Long spacecraftId, CharacterDTO characterDTO);
    List<CharacterDTO> getCharactersBySpacecraft(Long spacecraftId);
    List<CharacterDTO> getAllCharacters();
    void clearCache();
}
