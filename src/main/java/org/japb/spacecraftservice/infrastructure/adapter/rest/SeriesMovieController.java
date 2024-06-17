package org.japb.spacecraftservice.infrastructure.adapter.rest;
import org.japb.spacecraftservice.application.service.SeriesMovieService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seriesmovies")
public class SeriesMovieController implements SeriesMovieControllerSpec {
    private final SeriesMovieService seriesMovieService;

    public SeriesMovieController(SeriesMovieService seriesMovieService) {
        this.seriesMovieService = seriesMovieService;
    }


    @Override
    public ResponseEntity<SeriesMovieDTO> createSeriesMovie(SeriesMovieDTO seriesMovieDTO) {
        SeriesMovieDTO createdSeriesMovie = seriesMovieService.createSeriesMovie(seriesMovieDTO);
        var seriesMovie = new SeriesMovie();
        BeanUtils.copyProperties(seriesMovieDTO, seriesMovie);
        //return ResponseEntity.status(HttpStatus.CREATED).body(seriesMovieRepository.save(seriesMovie));
        return ResponseEntity.status(201).body(createdSeriesMovie);
    }

    @Override
    public ResponseEntity<List<SeriesMovieDTO>> getAllSeriesMovies() {
        List<SeriesMovieDTO> seriesMovies = seriesMovieService.getAllSeriesMovies();
        return new ResponseEntity<>(seriesMovies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeriesMovieDTO> getSeriesMovieById(Long serieId) {
        SeriesMovieDTO seriesMovie = seriesMovieService.getSeriesMovieById(serieId);
        return new ResponseEntity<>(seriesMovie, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeriesMovieDTO> updateSeriesMovie(Long serieId, SeriesMovieDTO seriesMovieDTO) {
        SeriesMovieDTO updatedSeriesMovie = seriesMovieService.updateSeriesMovie(serieId, seriesMovieDTO);
        return new ResponseEntity<>(updatedSeriesMovie, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSeriesMovie(Long serieId) {
        seriesMovieService.deleteSeriesMovie(serieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
