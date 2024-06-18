-- Datos para la tabla series_movie
INSERT INTO TB_SERIES_MOVIE (title, release_date, type) VALUES ('Star Wars: Episode IV', '1977-05-25', 'Movie'),
('Star Trek: The Original Series', '1966-09-08', 'Series'),
('Battlestar Galactica', '2004-10-18', 'Series');

-- Datos para la tabla spacecraft
INSERT INTO TB_SPACECRAFT (name, model, manufacturer, length, crew_capacity, passenger_capacity, series_movie_id) VALUES
('Millennium Falcon', 'YT-1300 light freighter', 'Corellian Engineering Corporation', 34.75, 6, 6, 1),
('USS Enterprise', 'Constitution-class', 'Starfleet', 288.6, 430, 0, 2),
('Galactica', 'Battlestar', 'Colonial Fleet', 1438, 2000, 5000, 3);

-- Datos para la tabla character
INSERT INTO TB_SPACE_CHARACTER (name, role, species, gender, birth_date, spacecraft_id) VALUES
('Han Solo', 'Captain', 'Human', 'Male', '1942-07-12', 1),
('Chewbacca', 'Co-pilot', 'Wookiee', 'Male', '0000-01-01', 1), -- El año 200 BBY no puede representarse en formato de fecha estándar, por lo que se usa un placeholder
('James T. Kirk', 'Captain', 'Human', 'Male', '2233-03-22', 2),
('Spock', 'First Officer', 'Vulcan', 'Male', '2230-01-06', 2),
('William Adama', 'Commander', 'Human', 'Male', '1945-10-19', 3),
('Kara Thrace', 'Pilot', 'Human', 'Female', '1980-01-10', 3);