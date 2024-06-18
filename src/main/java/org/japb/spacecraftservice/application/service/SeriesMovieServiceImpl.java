package org.japb.spacecraftservice.application.service;

import lombok.extern.log4j.Log4j2;
import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.japb.spacecraftservice.domain.repository.SeriesMovieRepository;
import org.springframework.beans.BeanUtils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SeriesMovieServiceImpl implements SeriesMovieService{

    private final SeriesMovieRepository seriesMovieRepository;

    public SeriesMovieServiceImpl(SeriesMovieRepository seriesMovieRepository) {
        this.seriesMovieRepository = seriesMovieRepository;
    }

    @Override
    @Transactional
    public SeriesMovieDTO createSeriesMovie(SeriesMovieDTO seriesMovieDTO) {
        log.info("Creating SeriesMovie: {}", seriesMovieDTO);
        var seriesMovie = new SeriesMovie();
        BeanUtils.copyProperties(seriesMovieDTO, seriesMovie);
        SeriesMovie savedSeriesMovie = seriesMovieRepository.save(seriesMovie);
        log.info("Created SeriesMovie with ID: {}", savedSeriesMovie.getId());
        return convertToDTO(savedSeriesMovie);
    }


    @Override
    public List<SeriesMovieDTO> getAllSeriesMovies() {
        log.info("Fetching all SeriesMovies from database");
        List<SeriesMovie> seriesMovies = seriesMovieRepository.findAll();
        log.debug("Found {} SeriesMovies", seriesMovies.size());
        return seriesMovies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Cacheable(value = "seriesMovieCache", key = "#serieId")
    @Override
    public SeriesMovieDTO getSeriesMovieById(Long serieId) {
        log.info("Fetching SeriesMovie by ID: {}", serieId);
        SeriesMovie seriesMovie = seriesMovieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id: " + serieId));
        log.debug("Found SeriesMovie: {}", seriesMovie);
        return convertToDTO(seriesMovie);
    }


    @Override
    @Transactional
    public SeriesMovieDTO updateSeriesMovie(Long serieId, SeriesMovieDTO seriesMovieDTO) {
        log.info("Updating SeriesMovie with ID {}: {}", serieId, seriesMovieDTO);
        SeriesMovie seriesMovie = seriesMovieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id: " + serieId));
        BeanUtils.copyProperties(seriesMovieDTO, seriesMovie);
        seriesMovie.setId(serieId);
        SeriesMovie updatedSeriesMovie = seriesMovieRepository.save(seriesMovie);
        log.info("Updated SeriesMovie: {}", updatedSeriesMovie);
        return convertToDTO(updatedSeriesMovie);
    }

    @Override
    @Transactional
    public void deleteSeriesMovie(Long serieId) {
        log.info("Deleting SeriesMovie with ID: {}", serieId);
        SeriesMovie seriesMovie = seriesMovieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("SeriesMovie not found with id: " + serieId));
        seriesMovieRepository.delete(seriesMovie);
        log.info("Deleted SeriesMovie: {}", seriesMovie);
    }

    protected SeriesMovieDTO convertToDTO(SeriesMovie seriesMovie) {
        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        BeanUtils.copyProperties(seriesMovie, seriesMovieDTO);

        if (seriesMovie.getSpacecrafts() != null) {
            List<SpacecraftDTO> spacecraftDTOs = seriesMovie.getSpacecrafts().stream()
                    .map(spacecraft -> {
                        SpacecraftDTO spacecraftDTO = new SpacecraftDTO();
                        BeanUtils.copyProperties(spacecraft, spacecraftDTO);
                        spacecraftDTO.setSeriesMovieId(spacecraft.getSeriesMovie().getId());

                        if (spacecraft.getCharacters() != null) {
                            List<CharacterDTO> characterDTOs = spacecraft.getCharacters().stream()
                                    .map(character -> {
                                        CharacterDTO characterDTO = new CharacterDTO();
                                        BeanUtils.copyProperties(character, characterDTO);
                                        characterDTO.setSpacecraftId(character.getSpacecraft().getSpaceId());
                                        return characterDTO;
                                    })
                                    .collect(Collectors.toList());
                            spacecraftDTO.setCharacters(characterDTOs);
                        }
                        return spacecraftDTO;
                    })
                    .collect(Collectors.toList());
            seriesMovieDTO.setSpacecrafts(spacecraftDTOs);
        }

        return seriesMovieDTO;
    }
}
