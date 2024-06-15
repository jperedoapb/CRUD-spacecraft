package org.japb.spacecraftservice.domain.repository;

import org.japb.spacecraftservice.domain.model.SpaceCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<SpaceCharacter, Long> {
    List<SpaceCharacter> findBySpacecraftSpaceId(Long spacecraftId);
}
