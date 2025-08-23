package com.real.interview.service.impl;

import com.real.interview.entity.MovieEntity;
import com.real.interview.mapper.MovieMapper;
import com.real.interview.model.Movie;
import com.real.interview.repository.MovieRepository;
import com.real.interview.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private MovieMapper movieMapperImpl;

    private final int maxRetry = 5;
    private int retryCount = 0;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapperImpl) {
        this.movieRepository = movieRepository;
        this.movieMapperImpl = movieMapperImpl;
    }

    /**
     * Get all movies
     *
     * @param pageable pagination info
     * @return movies - page of movies
     */
    @Override
    public Page<Movie> getAll(Pageable pageable) {
        Page<MovieEntity> movieEntities =  movieRepository.findAll(pageable);
        Page<Movie> movies = new PageImpl<Movie>(
                movieEntities.stream()
                        .map(me -> movieMapperImpl.fromEntity(me))
                        .collect(Collectors.toList()),
                pageable,
                movieEntities.getTotalElements()
        );
        return  movies;
    }

    /**
     * Add a movie
     *
     * @param movie
     * @return - status
     */
    @Override
    public String addMovie(Movie movie) {
        String status = "SUCCESS";
        MovieEntity movieEntity = movieMapperImpl.toEntity(movie);
        try {
            movieRepository.save(movieEntity);
        } catch (OptimisticLockingFailureException olfe) {
            if(retryCount < maxRetry) {
                log.error("OptimisticLockingFailureException... retrying.");
                retryCount++;
                addMovie(movie);
            }
            status = "FAILED";
        } catch (Exception e) {
            status = "FAILED";
            log.error("e: ", e);
        }
        return status;
    }
}
