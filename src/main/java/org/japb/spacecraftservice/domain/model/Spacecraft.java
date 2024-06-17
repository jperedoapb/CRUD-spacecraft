package org.japb.spacecraftservice.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "TB_SPACECRAFT")
public class Spacecraft extends RepresentationModel<Spacecraft> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private Double length;
    @Column(nullable = false)
    private Integer crewCapacity;
    @Column(nullable = false)
    private Integer passengerCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_movie_id", nullable = false)
    private SeriesMovie seriesMovie;

    @OneToMany(mappedBy = "spacecraft", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpaceCharacter> characters = new ArrayList<>();
}
