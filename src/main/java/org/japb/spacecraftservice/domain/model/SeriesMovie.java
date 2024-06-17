package org.japb.spacecraftservice.domain.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="TB_SERIES_MOVIE")
public class SeriesMovie extends RepresentationModel<SeriesMovie> implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @Column(nullable = false)
    private String title;

  @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
  @Column(nullable = false)
    private String type; // "Series" o "Película"

  @OneToMany(mappedBy = "seriesMovie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spacecraft> spacecrafts;
}
