package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.CharacterDTO;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.japb.spacecraftservice.domain.repository.CharacterRepository;
import org.japb.spacecraftservice.domain.repository.SeriesMovieRepository;
import org.japb.spacecraftservice.domain.repository.SpacecraftRepository;
import org.japb.spacecraftservice.specification.SpacecraftSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpacecraftServiceImplTest {

    @InjectMocks
    private SpacecraftServiceImpl spacecraftService;

    @Mock
    private SpacecraftRepository spacecraftRepository;

    @Mock
    private SeriesMovieRepository seriesMovieRepository;

    @Mock
    private CharacterRepository characterRepository;

    private Spacecraft spacecraft;
    private Spacecraft spacecraft2;
    private SpacecraftDTO spacecraftDTO;
    private SeriesMovie seriesMovie1;
    private SeriesMovie seriesMovie2;

    @BeforeEach
    void setUp() {


        seriesMovie1 = new SeriesMovie();
        seriesMovie1.setId(1L);
        seriesMovie1.setTitle("Star Wars: Episode IV");
        seriesMovie1.setReleaseDate(LocalDate.of(1977, 5, 25));
        seriesMovie1.setType("Movie");

        seriesMovie2 = new SeriesMovie();
        seriesMovie2.setId(2L);
        seriesMovie2.setTitle("Star Trek: The Original Series");
        seriesMovie2.setReleaseDate(LocalDate.of(1966, 9, 8));
        seriesMovie2.setType("Series");

        spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("TIE Fighter");
        spacecraft.setModel("Twin Ion Engine starfighter");
        spacecraft.setManufacturer("Sienar Fleet Systems");
        spacecraft.setLength(6.4);
        spacecraft.setCrewCapacity(1);
        spacecraft.setPassengerCapacity(0);
        spacecraft.setSeriesMovie(seriesMovie1);

        spacecraft2 = new Spacecraft();
        spacecraft2.setSpaceId(2L);
        spacecraft2.setName("USS Enterprise");
        spacecraft2.setModel("Constitution-class");
        spacecraft2.setManufacturer("Starfleet");
        spacecraft2.setLength(288.6);
        spacecraft2.setCrewCapacity(430);
        spacecraft2.setPassengerCapacity(0);
        spacecraft2.setSeriesMovie(seriesMovie2);

        spacecraftDTO = new SpacecraftDTO();
        spacecraftDTO.setSpaceId(1L);
        spacecraftDTO.setName("TIE Fighter");
        spacecraftDTO.setModel("Twin Ion Engine starfighter");
        spacecraftDTO.setManufacturer("Sienar Fleet Systems");
        spacecraftDTO.setLength(6.4);
        spacecraftDTO.setCrewCapacity(1);
        spacecraftDTO.setPassengerCapacity(0);
        spacecraftDTO.setSeriesMovieId(1L);
        spacecraftDTO.setCharacters(Collections.singletonList(new CharacterDTO()));

        // Configuración del mock de SeriesMovie
        SeriesMovie seriesMovie = new SeriesMovie();
        seriesMovie.setId(1L);
        spacecraft.setSeriesMovie(seriesMovie);

        // Configuración de los mocks y respuestas
        lenient().when(spacecraftRepository.save(any(Spacecraft.class))).thenReturn(spacecraft);
        lenient().when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(seriesMovie));
    }

    @Test
    void testCreateSpacecraft() {
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(new SeriesMovie()));
        when(spacecraftRepository.save(any(Spacecraft.class))).thenReturn(spacecraft);

        SpacecraftDTO result = spacecraftService.createSpacecraft(spacecraftDTO);

        assertNotNull(result);
        assertEquals("TIE Fighter", result.getName());
        verify(spacecraftRepository, times(1)).save(any(Spacecraft.class));
    }

    @Test
    void testGetAllSpacecrafts() {
        when(spacecraftRepository.findAll()).thenReturn(Collections.singletonList(spacecraft));

        List<SpacecraftDTO> result = spacecraftService.getAllSpacecrafts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("TIE Fighter", result.get(0).getName());
    }

    @Test
    void testGetSpacecraftById() {
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));

        SpacecraftDTO result = spacecraftService.getSpacecraftById(1L);

        assertNotNull(result);
        assertEquals("TIE Fighter", result.getName());
    }

    @Test
    void testUpdateSpacecraft() {
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));
        when(spacecraftRepository.save(any(Spacecraft.class))).thenReturn(spacecraft);

        SpacecraftDTO result = spacecraftService.updateSpacecraft(1L, spacecraftDTO);

        assertNotNull(result);
        assertEquals("TIE Fighter", result.getName());
        verify(spacecraftRepository, times(1)).save(any(Spacecraft.class));
    }

    @Test
    void testDeleteSpacecraft() {
        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));

        spacecraftService.deleteSpacecraft(1L);

        verify(spacecraftRepository, times(1)).delete(any(Spacecraft.class));
    }

    @Test
    void testPartiallyUpdateSpacecraft() {
        // Preparación de datos
        Spacecraft spacecraft = new Spacecraft();
        spacecraft.setSpaceId(1L);
        spacecraft.setName("TIE Fighter");
        spacecraft.setModel("Twin Ion Engine starfighter");

        when(spacecraftRepository.findById(1L)).thenReturn(Optional.of(spacecraft));

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated TIE Fighter");
        updates.put("model", "Updated Twin Ion Engine starfighter");
        updates.put("seriesMovieId", 1L); // Agregamos seriesMovieId al mapa de actualizaciones

        SeriesMovie seriesMovie = new SeriesMovie();
        seriesMovie.setId(1L);
        seriesMovie.setTitle("Star Wars: Episode IV");
        seriesMovie.setReleaseDate(LocalDate.of(1977, 5, 25));
        seriesMovie.setType("Movie");

        // Simulación del comportamiento de findById en seriesMovieRepository
        when(seriesMovieRepository.findById(1L)).thenReturn(Optional.of(seriesMovie));

        // Simulación del comportamiento de save en el repositorio
        when(spacecraftRepository.save(any(Spacecraft.class))).thenAnswer(invocation -> {
            Spacecraft updatedSpacecraft = invocation.getArgument(0);
            return updatedSpacecraft;
        });

        // Ejecución del método en prueba
        SpacecraftDTO result = spacecraftService.partiallyUpdateSpacecraft(1L, updates);

        // Verificaciones
        assertNotNull(result);
        assertEquals("Updated TIE Fighter", result.getName());
        assertEquals("Updated Twin Ion Engine starfighter", result.getModel());

        // Verificar que se haya llamado findById con 1L en spacecraftRepository
        verify(spacecraftRepository, times(1)).findById(1L);

        // Verificar que se haya llamado findById con 1L en seriesMovieRepository
        verify(seriesMovieRepository, times(1)).findById(1L);

        // Verificación de interacciones con el repositorio
        verify(spacecraftRepository, times(1)).save(any(Spacecraft.class));
    }

    @Test
    public void testConvertCharacterDTOToEntity() {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(1L);
        characterDTO.setName("Han Solo");
        characterDTO.setRole("Captain");
        characterDTO.setSpecies("Human");
        characterDTO.setGender("Male");
        characterDTO.setBirthDate(LocalDate.of(1942, 7, 12));
        characterDTO.setSpacecraftId(1L);

        SpaceCharacter character = spacecraftService.convertCharacterDTOToEntity(characterDTO);

        assertNotNull(character);
        assertEquals(characterDTO.getId(), character.getId());
        assertEquals(characterDTO.getName(), character.getName());
        assertEquals(characterDTO.getRole(), character.getRole());
        assertEquals(characterDTO.getSpecies(), character.getSpecies());
        assertEquals(characterDTO.getGender(), character.getGender());
        assertEquals(characterDTO.getBirthDate(), character.getBirthDate());
    }

    @Test
    void testGetAllSpacecrafts1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Spacecraft> spacecraftPage = new PageImpl<>(Arrays.asList(spacecraft, spacecraft2), pageable, 2);

        when(spacecraftRepository.findAll(pageable)).thenReturn(spacecraftPage);

        Page<SpacecraftDTO> result = spacecraftService.getAllSpacecrafts1(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());

        SpacecraftDTO spacecraftDTO1 = result.getContent().get(0);
        assertEquals(spacecraft.getSpaceId(), spacecraftDTO1.getSpaceId());
        assertEquals(spacecraft.getName(), spacecraftDTO1.getName());

        SpacecraftDTO spacecraftDTO2 = result.getContent().get(1);
        assertEquals(spacecraft2.getSpaceId(), spacecraftDTO2.getSpaceId());
        assertEquals(spacecraft2.getName(), spacecraftDTO2.getName());

        verify(spacecraftRepository, times(1)).findAll(pageable);
    }


}