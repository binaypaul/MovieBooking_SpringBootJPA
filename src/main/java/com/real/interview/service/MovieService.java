package com.real.interview.service;

import com.real.interview.entity.MovieEntity;
import com.real.interview.exception.DataNotFoundException;
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

    /**
     * get a movie by id
     *
     * @param id
     * @return
     */
    MovieEntity getMovie(Long id) throws DataNotFoundException;

    /**
     * update a movie by id
     *
     * @param movieEntity
     * @return
     */
    MovieEntity update(MovieEntity movieEntity) throws DataNotFoundException;

    /**
     * delete a movie by id
     *
     * @param id
     * @return
     */
    String delete(Long id) throws DataNotFoundException;
}
