package org.japb.spacecraftservice.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
