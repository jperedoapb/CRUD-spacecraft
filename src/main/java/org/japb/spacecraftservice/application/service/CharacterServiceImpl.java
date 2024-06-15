package org.japb.spacecraftservice.application.service;

import lombok.extern.log4j.Log4j2;
import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.CharacterRepository;
import org.japb.spacecraftservice.domain.repository.SpacecraftRepository;
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
        Spacecraft spacecraft = spacecraftRepository.findById(spacecraftId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spacecraftId));

        SpaceCharacter character = new SpaceCharacter();
        character.setName(characterDTO.name());
        character.setRole(characterDTO.role());
        character.setSpecies(characterDTO.species());
        character.setGender(characterDTO.species());
        character.setBirthDate(characterDTO.birthDate());
        character.setSpacecraft(spacecraft);

        SpaceCharacter savedCharacter = characterRepository.save(character);

        return convertToDTO(savedCharacter);
    }

    @Override
    @Cacheable(value = "charactersCache", key = "#spacecraftId")
    public List<CharacterDTO> getCharactersBySpacecraft(Long spacecraftId) {
        List<SpaceCharacter> characters = characterRepository.findBySpacecraftSpaceId(spacecraftId);
        return characters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "allCharactersCache")
    public List<CharacterDTO> getAllCharacters() {
        List<SpaceCharacter> characters = characterRepository.findAll();
        return characters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = { "allCharactersCache" }, allEntries = true)
    public void clearCache() {
        log.info("Clear cache");
    }

    private CharacterDTO convertToDTO(SpaceCharacter character) {
        return new CharacterDTO(
        character.getId(),
        character.getName(),
        character.getRole(),
        character.getSpecies(),
        character.getGender(),
        character.getBirthDate(),
        character.getSpacecraft().getSpaceId()
        );
    }

}