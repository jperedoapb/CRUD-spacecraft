package org.japb.spacecraftservice.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
public class SeriesMovieDTO {
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;
    @NotBlank(message = "Title is mandatory")
    private String type;
    private List<SpacecraftDTO> spacecrafts;
}
