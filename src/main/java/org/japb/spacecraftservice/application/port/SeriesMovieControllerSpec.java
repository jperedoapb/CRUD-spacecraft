package org.japb.spacecraftservice.application.port;

import jakarta.validation.Valid;
import java.util.List;
import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SeriesMovieControllerSpec {
    @PostMapping("/series-movies")
    ResponseEntity<SeriesMovieDTO> createSeriesMovie(@Valid @RequestBody SeriesMovieDTO seriesMovieDTO);

    @GetMapping("/series-movies")
    ResponseEntity<List<SeriesMovieDTO>> getAllSeriesMovies();

    @GetMapping("/series-movies/{serieId}")
    ResponseEntity<SeriesMovieDTO> getSeriesMovieById(@PathVariable Long serieId);

    @PutMapping("/series-movies/{serieId}")
    ResponseEntity<SeriesMovieDTO> updateSeriesMovie(@PathVariable Long serieId,
                                                     @Valid @RequestBody SeriesMovieDTO seriesMovieDTO);

    @DeleteMapping("/series-movies/{serieId}")
    ResponseEntity<Void> deleteSeriesMovie(@PathVariable Long serieId);
}
