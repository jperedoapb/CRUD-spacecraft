package org.japb.spacecraftservice.domain.repository;

import org.japb.spacecraftservice.domain.model.Spacecraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpacecraftRepository extends JpaRepository<Spacecraft, Long>, JpaSpecificationExecutor<Spacecraft> {
    Page<Spacecraft> findAll(Pageable pageable);
}
