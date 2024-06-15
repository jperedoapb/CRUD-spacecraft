package org.japb.spacecraftservice.domain.repository;

import org.japb.spacecraftservice.domain.model.SeriesMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesMovieRepository extends JpaRepository<SeriesMovie, Long> {
}
