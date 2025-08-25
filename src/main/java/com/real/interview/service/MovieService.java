package com.real.interview.service;

import com.real.interview.entity.MovieEntity;
import com.real.interview.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    /**
     * Get all movies
     * @param pageable - pagination info
     * @return movies - page of movies
     */
    Page<Movie> getAll(Pageable pageable);

    /**
     * Add a movie
     * @param movie
     * @return - status
     */
    Movie addMovie(Movie movie);
}
