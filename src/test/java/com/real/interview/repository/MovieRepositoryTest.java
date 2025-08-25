package com.real.interview.repository;

import com.real.interview.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {

//Used for more control on the persistent context with using the Repository class or methods.
//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testSaveMovie() {
        MovieEntity movie = new MovieEntity();
        movie.setName("Test Movie");
        movie.setRating(8);

        MovieEntity savedMovie = movieRepository.save(movie);

        assertNotNull(savedMovie.getId());
        assertEquals("Test Movie", savedMovie.getName());
    }

    @Test
    void testFindById() {
        MovieEntity movie = new MovieEntity();
        movie.setName("Test Movie");
        movie.setRating(8);

//        MovieEntity savedMovie = entityManager.persistAndFlush(movie);
        MovieEntity savedMovie = movieRepository.save(movie);

        MovieEntity foundMovie = movieRepository.findById(savedMovie.getId()).orElse(null);

        assertNotNull(foundMovie);
        assertEquals("Test Movie", foundMovie.getName());
    }
}