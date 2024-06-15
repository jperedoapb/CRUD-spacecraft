package org.japb.spacecraftservice.domain.repository;

import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpacecraftRepository extends JpaRepository<Spacecraft, Long> {
}
