package org.japb.spacecraftservice.domain.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name= "TB_SPACE_CHARACTER")
public class SpaceCharacter extends RepresentationModel<SpaceCharacter> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String species;
    private String gender;
  @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "spacecraft_id", nullable = false)
    private Spacecraft spacecraft;
}
