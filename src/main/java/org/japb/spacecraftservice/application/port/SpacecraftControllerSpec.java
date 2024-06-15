package org.japb.spacecraftservice.application.port;

import jakarta.validation.Valid;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SpacecraftControllerSpec {
    ResponseEntity<SpacecraftDTO> createSpacecraft(@PathVariable Long seriesMovieId,
                                                   @Valid @RequestBody SpacecraftDTO spacecraftDTO);

    ResponseEntity<List<SpacecraftDTO>> getSpacecraftsBySeriesMovie(@PathVariable Long seriesMovieId);
}
