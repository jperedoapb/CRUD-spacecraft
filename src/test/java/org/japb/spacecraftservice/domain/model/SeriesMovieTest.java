package org.japb.spacecraftservice.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
public class SeriesMovieTest {

    @Test
    public void testEqualsAndHashCode() {
        SeriesMovie movie1 = new SeriesMovie();
        movie1.setId(1L);
        movie1.setTitle("Star Wars");
        movie1.setReleaseDate(LocalDate.of(1977, 5, 25));
        movie1.setType("Movie");

        SeriesMovie movie2 = new SeriesMovie();
        movie2.setId(1L);
        movie2.setTitle("Star Wars");
        movie2.setReleaseDate(LocalDate.of(1977, 5, 25));
        movie2.setType("Movie");

        assertThat(movie1).isEqualTo(movie2);
        assertThat(movie1.hashCode()).isEqualTo(movie2.hashCode());
    }

    @Test
    public void testSerializationDeserialization() {
        SeriesMovie originalMovie = new SeriesMovie();
        originalMovie.setId(1L);
        originalMovie.setTitle("Star Wars");
        originalMovie.setReleaseDate(LocalDate.of(1977, 5, 25));
        originalMovie.setType("Movie");
        originalMovie.setSpacecrafts(new ArrayList<>()); // Initialize spacecrafts list

        // Serialize the object
        byte[] serializedMovie = SerializationUtils.serialize(originalMovie);

        // Deserialize the object
        SeriesMovie deserializedMovie = (SeriesMovie) SerializationUtils.deserialize(serializedMovie);

        assertThat(deserializedMovie).isEqualTo(originalMovie);
    }
}
