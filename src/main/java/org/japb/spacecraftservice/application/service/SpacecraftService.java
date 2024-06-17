package org.japb.spacecraftservice.application.service;

import org.japb.spacecraftservice.application.dto.SpacecraftDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SpacecraftService {
    SpacecraftDTO createSpacecraft(SpacecraftDTO spacecraftDTO);
    List<SpacecraftDTO> getAllSpacecrafts();
    Page<SpacecraftDTO> getAllSpacecrafts1(Pageable pageable);
    SpacecraftDTO getSpacecraftById(Long spaceId);
    SpacecraftDTO updateSpacecraft(Long spaceId, SpacecraftDTO spacecraftDTO);
    void deleteSpacecraft(Long spaceId);
    SpacecraftDTO partiallyUpdateSpacecraft(Long spaceId, Map<String, Object> updates);
}
