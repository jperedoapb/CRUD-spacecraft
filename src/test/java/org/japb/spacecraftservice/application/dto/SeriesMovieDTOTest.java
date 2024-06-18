package org.japb.spacecraftservice.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SeriesMovieDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidSeriesMovieDTO() {
        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("Star Wars");
        seriesMovieDTO.setReleaseDate(LocalDate.of(2020, 1, 1));
        seriesMovieDTO.setType("Movie");

        Set<ConstraintViolation<SeriesMovieDTO>> violations = validator.validate(seriesMovieDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testTitleIsBlank() {
        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("");
        seriesMovieDTO.setReleaseDate(LocalDate.of(2020, 1, 1));
        seriesMovieDTO.setType("Movie");

        Set<ConstraintViolation<SeriesMovieDTO>> violations = validator.validate(seriesMovieDTO);

        assertEquals(1, violations.size());
        assertEquals("Title is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testReleaseDateInFuture() {
        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("Star Wars");
        seriesMovieDTO.setReleaseDate(LocalDate.of(2100, 1, 1));
        seriesMovieDTO.setType("Movie");

        Set<ConstraintViolation<SeriesMovieDTO>> violations = validator.validate(seriesMovieDTO);

        assertEquals(1, violations.size());
        assertEquals("Release date cannot be in the future", violations.iterator().next().getMessage());
    }

    @Test
    void testTypeIsBlank() {
        SeriesMovieDTO seriesMovieDTO = new SeriesMovieDTO();
        seriesMovieDTO.setTitle("Star Wars");
        seriesMovieDTO.setReleaseDate(LocalDate.of(2020, 1, 1));
        seriesMovieDTO.setType("");

        Set<ConstraintViolation<SeriesMovieDTO>> violations = validator.validate(seriesMovieDTO);

        assertEquals(1, violations.size());
        assertEquals("Title is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        SeriesMovieDTO dto1 = new SeriesMovieDTO();
        dto1.setId(1L);
        dto1.setTitle("Star Wars");
        dto1.setReleaseDate(LocalDate.of(2020, 1, 1));
        dto1.setType("Movie");

        SeriesMovieDTO dto2 = new SeriesMovieDTO();
        dto2.setId(1L);
        dto2.setTitle("Star Wars");
        dto2.setReleaseDate(LocalDate.of(2020, 1, 1));
        dto2.setType("Movie");

        SeriesMovieDTO dto3 = new SeriesMovieDTO();
        dto3.setId(2L);
        dto3.setTitle("Star Trek");
        dto3.setReleaseDate(LocalDate.of(2021, 1, 1));
        dto3.setType("Series");

        // Reflexividad
        assertEquals(dto1, dto1);
        assertEquals(dto2, dto2);
        assertEquals(dto3, dto3);

        // Simetría
        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);

        // Transitividad
        assertEquals(dto1, dto2);
        //assertEquals(dto2, dto3);
        //assertEquals(dto1, dto3);

        // Consistencia
        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Comparación con objeto null
        assertNotEquals(dto1, null);

        // Comparación con otro tipo de objeto
        assertNotEquals(dto1, "Star Wars");

        // Comparación con objetos diferentes
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto2, dto3);
    }
}
