CREATE TABLE IF NOT EXISTS TB_SERIES_MOVIE (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               title VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    type VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS TB_SPACECRAFT (
                                             space_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             name VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    length DOUBLE NOT NULL,
    crew_capacity INT NOT NULL,
    passenger_capacity INT NOT NULL,
    series_movie_id BIGINT NOT NULL,
    CONSTRAINT fk_series_movie
    FOREIGN KEY (series_movie_id)
    REFERENCES TB_SERIES_MOVIE(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS TB_SPACE_CHARACTER (
                                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                  name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    species VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    birth_date DATE,
    spacecraft_id BIGINT NOT NULL,
    CONSTRAINT fk_spacecraft
    FOREIGN KEY (spacecraft_id)
    REFERENCES TB_SPACECRAFT(space_id)
    ON DELETE CASCADE
    );