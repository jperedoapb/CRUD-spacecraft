package org.japb.spacecraftservice.application.service;

import lombok.extern.log4j.Log4j2;
import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.CharacterRepository;
import org.japb.spacecraftservice.domain.repository.SpacecraftRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final SpacecraftRepository spacecraftRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository, SpacecraftRepository spacecraftRepository) {
        this.characterRepository = characterRepository;
        this.spacecraftRepository = spacecraftRepository;
    }

    @Override
    @Transactional
    public CharacterDTO createCharacter(Long spacecraftId, CharacterDTO characterDTO) {
        log.info("Creating character: {}", characterDTO);
        Spacecraft spacecraft = spacecraftRepository.findById(spacecraftId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spacecraftId));

        var character = new SpaceCharacter();
        BeanUtils.copyProperties(characterDTO, character);
        character.setSpacecraft(spacecraft);

        SpaceCharacter savedCharacter = characterRepository.save(character);
        log.info("Created character with ID: {}", savedCharacter.getId());
        return convertToDTO(savedCharacter);
    }

    @Override
    @Cacheable(value = "charactersCache", key = "#spacecraftId")
    public List<CharacterDTO> getCharactersBySpacecraft(Long spacecraftId) {
        log.info("Fetching characters for spacecraft with ID: {}", spacecraftId);
        if (spacecraftId < 0) {
            throw new IllegalArgumentException("Spacecraft ID cannot be negative");
        }

        List<SpaceCharacter> characters = characterRepository.findBySpacecraftSpaceId(spacecraftId);

        if (characters.isEmpty()) {
            throw new ResourceNotFoundException("Characters not found for spacecraft ID " + spacecraftId);
        }
        log.debug("Found {} characters for spacecraft ID {}", characters.size(), spacecraftId);
        return characters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "allCharactersCache")
    public List<CharacterDTO> getAllCharacters() {
        log.info("Fetching all characters from database");
        List<SpaceCharacter> characters = characterRepository.findAll();
        log.debug("Found {} characters", characters.size());
        return characters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = { "allCharactersCache" }, allEntries = true)
    public void clearCache() {
        log.info("Clearing all characters cache");
    }

    private CharacterDTO convertToDTO(SpaceCharacter character) {
        var characterDTO = new CharacterDTO();
        BeanUtils.copyProperties(character, characterDTO);
        characterDTO.setSpacecraftId(character.getSpacecraft().getSpaceId());
        return characterDTO;
    }

}
