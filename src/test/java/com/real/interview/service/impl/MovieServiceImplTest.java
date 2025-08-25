package com.real.interview.service.impl;

import com.real.interview.entity.MovieEntity;
import com.real.interview.exception.DataNotFoundException;
import com.real.interview.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private MovieEntity movieEntity;

    @BeforeEach
    void setUp() {
        movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Test Movie");
        movieEntity.setRating(8);
    }

    @Test
    void testGetAll() {
        Page<MovieEntity> page = new PageImpl<>(List.of(movieEntity));
        when(movieRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<MovieEntity> result = movieService.getAll(PageRequest.of(0, 5));

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Test Movie", result.getContent().get(0).getName());
    }

    @Test
    void testAddMovie() {
        when(movieRepository.save(any(MovieEntity.class))).thenReturn(movieEntity);

        MovieEntity result = movieService.addMovie(movieEntity);

        assertNotNull(result);
        assertEquals("Test Movie", result.getName());
    }

    @Test
    void testGetMovie() throws DataNotFoundException {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movieEntity));

        MovieEntity result = movieService.getMovie(1L);

        assertNotNull(result);
        assertEquals("Test Movie", result.getName());
    }

    @Test
    void testGetMovie_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> movieService.getMovie(1L));
    }
}