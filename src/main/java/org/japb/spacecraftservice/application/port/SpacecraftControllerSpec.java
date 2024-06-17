package org.japb.spacecraftservice.application.port;

import jakarta.validation.Valid;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/spacecrafts")
public interface SpacecraftControllerSpec {
    @PostMapping
    ResponseEntity<SpacecraftDTO> createSpacecraft(@RequestBody SpacecraftDTO spacecraftDTO);

    @GetMapping
    ResponseEntity<List<SpacecraftDTO>> getAllSpacecrafts();


    @GetMapping("/datos/")
    ResponseEntity<Page<SpacecraftDTO>> getAllSpacecrafts1(@PageableDefault(page = 0, size = 10, sort = "spaceId", direction = Sort.Direction.ASC) Pageable pageable);

    @GetMapping("/{spaceId}")
    ResponseEntity<SpacecraftDTO> getSpacecraftById(@PathVariable Long spaceId);

    @PutMapping("/{spaceId}")
    ResponseEntity<SpacecraftDTO> updateSpacecraft(@PathVariable Long spaceId,
                                                   @RequestBody SpacecraftDTO spacecraftDTO);

    @DeleteMapping("/{spaceId}")
    ResponseEntity<Void> deleteSpacecraft(@PathVariable Long spaceId);

    @PatchMapping("/{spaceId}")
    ResponseEntity<SpacecraftDTO> partiallyUpdateSpacecraft(@PathVariable Long spaceId,
                                                            @RequestBody Map<String, Object> updates);
    /*
    ResponseEntity<SpacecraftDTO> createSpacecraft(@PathVariable Long seriesMovieId,
                                                   @Valid @RequestBody SpacecraftDTO spacecraftDTO);

    ResponseEntity<List<SpacecraftDTO>> getSpacecraftsBySeriesMovie(@PathVariable Long seriesMovieId);
     */
}
