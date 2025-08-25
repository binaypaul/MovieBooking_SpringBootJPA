package com.real.interview.service;

import com.real.interview.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    /**
     * Get all movies
     * @param pageable - pagination info
     * @return movies - page of movies
     */
    Page<MovieEntity> getAll(Pageable pageable);

    /**
     * Add a movie
     * @param movie
     * @return - status
     */
    MovieEntity addMovie(MovieEntity movie);
}
