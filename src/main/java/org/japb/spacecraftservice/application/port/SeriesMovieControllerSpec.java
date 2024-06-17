package org.japb.spacecraftservice.application.port;

import jakarta.validation.Valid;
import java.util.List;
import org.japb.spacecraftservice.application.dto.SeriesMovieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/seriesmovies")
public interface SeriesMovieControllerSpec {
    @PostMapping
    ResponseEntity<SeriesMovieDTO> createSeriesMovie(@Valid @RequestBody SeriesMovieDTO seriesMovieDTO);

    @GetMapping
    ResponseEntity<List<SeriesMovieDTO>> getAllSeriesMovies();

    @GetMapping("/{serieId}")
    ResponseEntity<SeriesMovieDTO> getSeriesMovieById(@PathVariable Long serieId);

    @PutMapping("/{serieId}")
    ResponseEntity<SeriesMovieDTO> updateSeriesMovie(@PathVariable Long serieId,
                                                     @Valid @RequestBody SeriesMovieDTO seriesMovieDTO);

    @DeleteMapping("/{serieId}")
    ResponseEntity<Void> deleteSeriesMovie(@PathVariable Long serieId);
}
