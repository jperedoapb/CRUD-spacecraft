package org.japb.spacecraftservice.infrastructure.adapter.rest;

import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.japb.spacecraftservice.application.port.SpacecraftControllerSpec;
import org.japb.spacecraftservice.application.service.SpacecraftService;
import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/spacecrafts")
public class SpacecraftController implements SpacecraftControllerSpec {

    private final SpacecraftService spacecraftService;

    public SpacecraftController(SpacecraftService spacecraftService) {
        this.spacecraftService = spacecraftService;
    }

    @Override
    public ResponseEntity<SpacecraftDTO> createSpacecraft(SpacecraftDTO spacecraftDTO) {
        SpacecraftDTO createdSpacecraft = spacecraftService.createSpacecraft(spacecraftDTO);
        return new ResponseEntity<>(createdSpacecraft, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<SpacecraftDTO>> getAllSpacecrafts1(
            @PageableDefault(page = 0, size = 10, sort = "spaceId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<SpacecraftDTO> spacecraftsPage = spacecraftService.getAllSpacecrafts1(pageable);
        return ResponseEntity.ok(spacecraftsPage);
    }


    @Override
    public ResponseEntity<List<SpacecraftDTO>> getAllSpacecrafts() {
        List<SpacecraftDTO> spacecrafts = spacecraftService.getAllSpacecrafts();
        return new ResponseEntity<>(spacecrafts, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<SpacecraftDTO> getSpacecraftById(Long spaceId) {
        SpacecraftDTO spacecraft = spacecraftService.getSpacecraftById(spaceId);
        return new ResponseEntity<>(spacecraft, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SpacecraftDTO> updateSpacecraft(Long spaceId, SpacecraftDTO spacecraftDTO) {
        SpacecraftDTO updatedSpacecraft = spacecraftService.updateSpacecraft(spaceId, spacecraftDTO);
        return new ResponseEntity<>(updatedSpacecraft, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSpacecraft(Long spaceId) {
        spacecraftService.deleteSpacecraft(spaceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<SpacecraftDTO> partiallyUpdateSpacecraft(Long spaceId, Map<String, Object> updates) {
        SpacecraftDTO updatedSpacecraft = spacecraftService.partiallyUpdateSpacecraft(spaceId, updates);
        return ResponseEntity.ok(updatedSpacecraft);
    }

    @Override
    public ResponseEntity<List<SpacecraftDTO>> searchSpacecrafts(String name) {
        List<SpacecraftDTO> spacecrafts = spacecraftService.searchSpacecrafts(name);

        return ResponseEntity.ok(spacecrafts);
    }


}
