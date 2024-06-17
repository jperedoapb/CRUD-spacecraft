package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.domain.exception.ResourceNotFoundException;
import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.SeriesMovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeriesMovieServiceImplTest {

    @Mock
    private SeriesMovieRepository seriesMovieRepository;

    @InjectMocks
    private SeriesMovieServiceImpl seriesMovieService;

    private SeriesMovie seriesMovie1;
    private SeriesMovie seriesMovie2;


    private Spacecraft spacecraft;
    private SpaceCharacter spaceCharacter;

    @BeforeEach
    void setUp() {
        seriesMovie1 = new SeriesMovie();
        seriesMovie1.setId(1L);
        seriesMovie1.setTitle("New Series Movie");
        seriesMovie1.setReleaseDate(LocalDate.of(1977, 5, 25));
        seriesMovie1.setType("Movie");

        seriesMovie2 = new SeriesMovie();
        seriesMovie2.setId(2L);
        seriesMovie2.setTitle("Series Movie 2");

        spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("Millennium Falcon");
        spacecraft.setModel("YT-1300 light freighter");
        spacecraft.setManufacturer("Corellian Engineering Corporation");
        spacecraft.setLength(34.75);
        spacecraft.setCrewCapacity(6);
        spacecraft.setPassengerCapacity(6);
        spacecraft.setSeriesMovie(seriesMovie1);

        spaceCharacter = new SpaceCharacter();
        spaceCharacter.setId(1L);
        spaceCharacter.setName("Han Solo");
        spaceCharacter.setRole("Captain");
        spaceCharacter.setSpecies("Human");
        spaceCharacter.setGender("Male");
        spaceCharacter.setBirthDate(LocalDate.of(1942, 7, 12));
        spaceCharacter.setSpacecraft(spacecraft);

        spacecraft.setCharacters(Collections.singletonList(spaceCharacter));
        seriesMovie1.setSpacecrafts(Collections.singletonList(spacecraft));
    }

    @Test
    void testCreateSeriesMovie() {
        // Mock repository behavior
        when(seriesMovieRepository.save(any(SeriesMovie.class))).thenReturn(seriesMovie1);

        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("New Series Movie");

        SeriesMovieDTO createdSeriesMovie = seriesMovieService.createSeriesMovie(seriesMovieDTO);

        assertNotNull(createdSeriesMovie);
        assertEquals(seriesMovie1.getId(), createdSeriesMovie.getId());
        assertEquals(seriesMovieDTO.getTitle(), createdSeriesMovie.getTitle());

        verify(seriesMovieRepository, times(1)).save(any(SeriesMovie.class));
    }

    @Test
    void testCreateSeriesMovie_TitleCheck() {
        // Mock repository behavior
        when(seriesMovieRepository.save(any(SeriesMovie.class))).thenAnswer(invocation -> {
            SeriesMovie seriesMovieArgument = invocation.getArgument(0);
            assertEquals("New Series Movie", seriesMovieArgument.getTitle());
            return seriesMovie1;
        });

        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("New Series Movie");

        SeriesMovieDTO createdSeriesMovie = seriesMovieService.createSeriesMovie(seriesMovieDTO);

        assertNotNull(createdSeriesMovie);
        assertEquals(seriesMovie1.getId(), createdSeriesMovie.getId());
        System.out.println("El objeto es: " + createdSeriesMovie.toString());
        assertEquals(seriesMovieDTO.getTitle(), createdSeriesMovie.getTitle());

        verify(seriesMovieRepository, times(1)).save(any(SeriesMovie.class));
    }

    @Test
    void testGetAllSeriesMovies() {
        // Mock repository behavior
        when(seriesMovieRepository.findAll()).thenReturn(Arrays.asList(seriesMovie1, seriesMovie2));

        List<SeriesMovieDTO> seriesMovies = seriesMovieService.getAllSeriesMovies();

        assertNotNull(seriesMovies);
        assertEquals(2, seriesMovies.size());

        SeriesMovieDTO dto1 = seriesMovies.get(0);
        assertEquals(seriesMovie1.getId(), dto1.getId());
        assertEquals(seriesMovie1.getTitle(), dto1.getTitle());

        SeriesMovieDTO dto2 = seriesMovies.get(1);
        assertEquals(seriesMovie2.getId(), dto2.getId());
        assertEquals(seriesMovie2.getTitle(), dto2.getTitle());

        verify(seriesMovieRepository, times(1)).findAll();
    }

    @Test
    void testGetSeriesMovieById() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(seriesMovie1));

        SeriesMovieDTO foundSeriesMovie = seriesMovieService.getSeriesMovieById(1L);

        assertNotNull(foundSeriesMovie);
        assertEquals(seriesMovie1.getId(), foundSeriesMovie.getId());
        assertEquals(seriesMovie1.getTitle(), foundSeriesMovie.getTitle());

        verify(seriesMovieRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSeriesMovieById_NotFound() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            seriesMovieService.getSeriesMovieById(1L);
        });

        String expectedMessage = "SeriesMovie not found with id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(seriesMovieRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateSeriesMovie() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(seriesMovie1));
        when(seriesMovieRepository.save(any(SeriesMovie.class))).thenReturn(seriesMovie1);

        SeriesMovieDTO updatedSeriesMovieDTO = new SeriesMovieDTO();
        updatedSeriesMovieDTO.setTitle("Updated Title");

        SeriesMovieDTO updatedSeriesMovie = seriesMovieService.updateSeriesMovie(1L, updatedSeriesMovieDTO);

        assertNotNull(updatedSeriesMovie);
        assertEquals(seriesMovie1.getId(), updatedSeriesMovie.getId());
        assertEquals(updatedSeriesMovieDTO.getTitle(), updatedSeriesMovie.getTitle());

        verify(seriesMovieRepository, times(1)).findById(1L);
        verify(seriesMovieRepository, times(1)).save(any(SeriesMovie.class));
    }

    @Test
    void testUpdateSeriesMovie_NotFound() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.empty());

        SeriesMovieDTO updatedSeriesMovieDTO = new SeriesMovieDTO();
        updatedSeriesMovieDTO.setTitle("Updated Title");

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            seriesMovieService.updateSeriesMovie(1L, updatedSeriesMovieDTO);
        });

        String expectedMessage = "SeriesMovie not found with id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(seriesMovieRepository, times(1)).findById(1L);
        verify(seriesMovieRepository, never()).save(any(SeriesMovie.class));
    }

    @Test
    void testDeleteSeriesMovie() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(seriesMovie1));

        seriesMovieService.deleteSeriesMovie(1L);

        verify(seriesMovieRepository, times(1)).findById(1L);
        verify(seriesMovieRepository, times(1)).delete(seriesMovie1);
    }

    @Test
    void testDeleteSeriesMovie_NotFound() {
        // Mock repository behavior
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            seriesMovieService.deleteSeriesMovie(1L);
        });

        String expectedMessage = "SeriesMovie not found with id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(seriesMovieRepository, times(1)).findById(1L);
        verify(seriesMovieRepository, never()).delete(any(SeriesMovie.class));
    }

    @Test
    void testConvertToDTO() {
        // Llamada al método
        SeriesMovieDTO seriesMovieDTO = seriesMovieService.convertToDTO(seriesMovie1);

        // Verificaciones
        assertNotNull(seriesMovieDTO);
        assertEquals(seriesMovie1.getId(), seriesMovieDTO.getId());
        assertEquals(seriesMovie1.getTitle(), seriesMovieDTO.getTitle());
        assertEquals(seriesMovie1.getReleaseDate(), seriesMovieDTO.getReleaseDate());
        assertEquals(seriesMovie1.getType(), seriesMovieDTO.getType());

        // Verificar spacecrafts
        assertNotNull(seriesMovieDTO.getSpacecrafts());
        assertEquals(1, seriesMovieDTO.getSpacecrafts().size());

        SpacecraftDTO spacecraftDTO = seriesMovieDTO.getSpacecrafts().get(0);
        assertEquals(spacecraft.getSpaceId(), spacecraftDTO.getSpaceId());
        assertEquals(spacecraft.getName(), spacecraftDTO.getName());
        assertEquals(spacecraft.getModel(), spacecraftDTO.getModel());
        assertEquals(spacecraft.getManufacturer(), spacecraftDTO.getManufacturer());
        assertEquals(spacecraft.getLength(), spacecraftDTO.getLength());
        assertEquals(spacecraft.getCrewCapacity(), spacecraftDTO.getCrewCapacity());
        assertEquals(spacecraft.getPassengerCapacity(), spacecraftDTO.getPassengerCapacity());
        assertEquals(seriesMovie1.getId(), spacecraftDTO.getSeriesMovieId());

        // Verificar characters
        assertNotNull(spacecraftDTO.getCharacters());
        assertEquals(1, spacecraftDTO.getCharacters().size());

        CharacterDTO characterDTO = spacecraftDTO.getCharacters().get(0);
        assertEquals(spaceCharacter.getId(), characterDTO.getId());
        assertEquals(spaceCharacter.getName(), characterDTO.getName());
        assertEquals(spaceCharacter.getRole(), characterDTO.getRole());
        assertEquals(spaceCharacter.getSpecies(), characterDTO.getSpecies());
        assertEquals(spaceCharacter.getGender(), characterDTO.getGender());
        assertEquals(spaceCharacter.getBirthDate(), characterDTO.getBirthDate());
        assertEquals(spacecraft.getSpaceId(), characterDTO.getSpacecraftId());
    }
}
