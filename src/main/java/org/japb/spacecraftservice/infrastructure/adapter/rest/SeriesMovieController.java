package org.japb.spacecraftservice.infrastructure.adapter.rest;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import java.time.LocalDate;
import java.util.ArrayList;

import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;
import org.japb.spacecraftservice.application.port.SeriesMovieControllerSpec;
import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.japb.spacecraftservice.domain.repository.SeriesMovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeriesMovieController implements SeriesMovieControllerSpec {
    private final SeriesMovieRepository seriesMovieRepository;

    public SeriesMovieController(SeriesMovieRepository seriesMovieRepository) {
        this.seriesMovieRepository = seriesMovieRepository;
    }

    @Override
    public ResponseEntity<SeriesMovieDTO> createSeriesMovie(SeriesMovieDTO seriesMovieDTO) {
        var seriesMovie = new SeriesMovie();
        BeanUtils.copyProperties(seriesMovieDTO, seriesMovie);
        //return ResponseEntity.status(HttpStatus.CREATED).body(seriesMovieRepository.save(seriesMovie));
        return null;
    }

    @Override
    public ResponseEntity<List<SeriesMovieDTO>> getAllSeriesMovies() {
        return null;
    }

    @Override
    public ResponseEntity<SeriesMovieDTO> getSeriesMovieById(Long serieId) {
        return null;
    }

    @Override
    public ResponseEntity<SeriesMovieDTO> updateSeriesMovie(Long serieId, SeriesMovieDTO seriesMovieDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSeriesMovie(Long serieId) {
        return null;
    }
}
