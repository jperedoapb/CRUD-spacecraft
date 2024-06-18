package org.japb.spacecraftservice.application.service;

import lombok.extern.log4j.Log4j2;
import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.CharacterRepository;
import org.japb.spacecraftservice.domain.repository.SeriesMovieRepository;
import org.japb.spacecraftservice.domain.repository.SpacecraftRepository;
import org.japb.spacecraftservice.specification.SpacecraftSpecifications;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SpacecraftServiceImpl implements SpacecraftService {
    private final SpacecraftRepository spacecraftRepository;
    private final CharacterRepository characterRepository;
    private final SeriesMovieRepository seriesMovieRepository;

    public SpacecraftServiceImpl(SpacecraftRepository spacecraftRepository, SeriesMovieRepository seriesMovieRepository,
                                 CharacterRepository characterRepository) {
        this.spacecraftRepository = spacecraftRepository;
        this.seriesMovieRepository = seriesMovieRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    @Transactional
    public SpacecraftDTO createSpacecraft(SpacecraftDTO spacecraftDTO) {
        log.info("Creating spacecraft: {}", spacecraftDTO.getName());
        Spacecraft spacecraft = convertToEntity(spacecraftDTO);

        // Fetch the SeriesMovie entity by ID and set it to the spacecraft
        SeriesMovie seriesMovie = seriesMovieRepository.findById(spacecraftDTO.getSeriesMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id " + spacecraftDTO.getSeriesMovieId()));
        spacecraft.setSeriesMovie(seriesMovie);

        spacecraft = spacecraftRepository.save(spacecraft);

        if (spacecraftDTO.getCharacters() != null) {
            List<SpaceCharacter> characters = spacecraftDTO.getCharacters().stream()
                    .map(this::convertCharacterDTOToEntity)
                    .collect(Collectors.toList());

            for (SpaceCharacter character : characters) {
                character.setSpacecraft(spacecraft); // Ensure the spacecraft is set before saving
                characterRepository.save(character);
            }

            spacecraft.setCharacters(characters);
        }
        log.info("Spacecraft created successfully: {}", spacecraft.getName());
        return convertToDTO(spacecraft);
    }

    @Override
    public List<SpacecraftDTO> getAllSpacecrafts() {
        log.info("Fetching all spacecrafts from database");
        List<Spacecraft> spacecrafts = spacecraftRepository.findAll();
        log.debug("Found {} spacecrafts", spacecrafts.size());
        return spacecrafts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SpacecraftDTO> getAllSpacecrafts1(Pageable pageable) {
        log.info("Fetching all spacecrafts with pagination");
        Page<Spacecraft> spacecraftsPage = spacecraftRepository.findAll(pageable);
        log.debug("Found {} spacecrafts", spacecraftsPage.getTotalElements());
        return spacecraftsPage.map(this::convertToDTO);
    }


    @Override
    public SpacecraftDTO getSpacecraftById(Long spaceId) {
        log.info("Fetching spacecraft by id: {}", spaceId);
        Spacecraft spacecraft = spacecraftRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spaceId));
        return convertToDTO(spacecraft);
    }

    @Override
    public SpacecraftDTO updateSpacecraft(Long spaceId, SpacecraftDTO spacecraftDTO) {
        log.info("Updating spacecraft with id: {}", spaceId);
        Spacecraft spacecraft = spacecraftRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spaceId));

        BeanUtils.copyProperties(spacecraftDTO, spacecraft, "spaceId");

        if (spacecraftDTO.getSeriesMovieId() != null) {
            SeriesMovie seriesMovie = seriesMovieRepository.findById(spacecraftDTO.getSeriesMovieId())
                    .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id " + spacecraftDTO.getSeriesMovieId()));
            spacecraft.setSeriesMovie(seriesMovie);
        } else {
            spacecraft.setSeriesMovie(null);
        }

        Spacecraft updatedSpacecraft = spacecraftRepository.save(spacecraft);
        log.info("Spacecraft updated successfully: {}", updatedSpacecraft.getName());
        return convertToDTO(updatedSpacecraft);
    }

    @Override
    public void deleteSpacecraft(Long spaceId) {
        log.info("Deleting spacecraft with id: {}", spaceId);
        Spacecraft spacecraft = spacecraftRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spaceId));
        spacecraftRepository.delete(spacecraft);
        log.info("Spacecraft deleted successfully: {}", spaceId);
    }

    @Override
    @Transactional
    public SpacecraftDTO partiallyUpdateSpacecraft(Long spaceId, Map<String, Object> updates) {
        log.info("Partially updating spacecraft with id: {}", spaceId);
        Spacecraft existingSpacecraft = spacecraftRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Spacecraft not found with id " + spaceId));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Spacecraft.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingSpacecraft, value);
            }
        });

        if (updates.containsKey("seriesMovieId")) {
            Long seriesMovieId = (Long) updates.get("seriesMovieId");
            SeriesMovie seriesMovie = seriesMovieRepository.findById(seriesMovieId)
                    .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id " + seriesMovieId));
            existingSpacecraft.setSeriesMovie(seriesMovie);
        }

        Spacecraft updatedSpacecraft = spacecraftRepository.save(existingSpacecraft);
        log.info("Spacecraft partially updated successfully: {}", updatedSpacecraft.getName());
        return convertToDTO(updatedSpacecraft);
    }

    @Override
    public List<SpacecraftDTO> searchSpacecrafts(String name) {
        log.info("Searching spacecrafts by name: {}", name);
        Specification<Spacecraft> spec = SpacecraftSpecifications.nameContainsIgnoreCase(name);
        List<Spacecraft> spacecrafts = spacecraftRepository.findAll(spec);
        log.debug("Found {} spacecrafts matching the search criteria", spacecrafts.size());
        return spacecrafts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private Spacecraft convertToEntity(SpacecraftDTO spacecraftDTO) {
        Spacecraft spacecraft = new Spacecraft();
        BeanUtils.copyProperties(spacecraftDTO, spacecraft);
        return spacecraft;
    }

    private SpacecraftDTO convertToDTO(Spacecraft spacecraft) {
        SpacecraftDTO spacecraftDTO = new SpacecraftDTO();
        BeanUtils.copyProperties(spacecraft, spacecraftDTO);
        if (spacecraft.getCharacters() != null) {
            List<CharacterDTO> characterDTOs = spacecraft.getCharacters().stream()
                    .map(this::convertCharacterEntityToDTO)
                    .collect(Collectors.toList());
            spacecraftDTO.setCharacters(characterDTOs);
        }
        spacecraftDTO.setSeriesMovieId(spacecraft.getSeriesMovie().getId());
        return spacecraftDTO;
    }

    protected SpaceCharacter convertCharacterDTOToEntity(CharacterDTO characterDTO) {
        SpaceCharacter character = new SpaceCharacter();
        BeanUtils.copyProperties(characterDTO, character);
        return character;
    }

    protected CharacterDTO convertCharacterEntityToDTO(SpaceCharacter character) {
        CharacterDTO characterDTO = new CharacterDTO();
        BeanUtils.copyProperties(character, characterDTO);
        characterDTO.setSpacecraftId(character.getSpacecraft().getSpaceId());
        return characterDTO;
    }

}
