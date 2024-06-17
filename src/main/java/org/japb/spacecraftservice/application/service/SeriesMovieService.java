package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;

import java.util.List;

public interface SeriesMovieService {
    SeriesMovieDTO createSeriesMovie(SeriesMovieDTO seriesMovieDTO);

    List<SeriesMovieDTO> getAllSeriesMovies();

    SeriesMovieDTO getSeriesMovieById(Long serieId);

    SeriesMovieDTO updateSeriesMovie(Long serieId, SeriesMovieDTO seriesMovieDTO);

    void deleteSeriesMovie(Long serieId);
}
