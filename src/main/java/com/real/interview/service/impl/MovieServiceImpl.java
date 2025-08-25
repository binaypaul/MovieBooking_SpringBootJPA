package com.real.interview.service.impl;

import com.real.interview.entity.CastEntity;
import com.real.interview.entity.MovieEntity;
import com.real.interview.exception.DataNotFoundException;
import com.real.interview.repository.CastRepository;
import com.real.interview.repository.MovieRepository;
import com.real.interview.service.MovieService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final MovieRepository movieRepository;
    private final CastRepository castRepository;

    private final int maxRetry = 5;
    private int retryCount = 0;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CastRepository castRepository) {
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
    }

    /**
     * Get all movies
     *
     * @param pageable pagination info
     * @return movies - page of movies
     */
    @Override
    public Page<MovieEntity> getAll(Pageable pageable) {
        Page<MovieEntity> movieEntities =  movieRepository.findAll(pageable);
        return  movieEntities;
    }

    /**
     * Add a movie
     *
     * @param movieEntity
     * @return - status
     */
    @Override
    public MovieEntity addMovie(MovieEntity movieEntity) {
        MovieEntity resme = null;
        try {
            if(null != movieEntity.getReviews()) {
                movieEntity.getReviews().forEach(r->r.setMovie(movieEntity));
            }
            if(null!=movieEntity.getCasts()) {
                List<CastEntity> mergedCasts = movieEntity.getCasts().stream()
                        .map(ce-> {
                            try {
                                CastEntity cee = castRepository.getCastByName(ce.getName());
                                if(cee != null)
                                    return cee;
                                else
                                    return ce;
                            } catch (Exception e) {
                                return ce;
                            }
                        })
                        .toList();
                movieEntity.setCasts(mergedCasts);
            }
            resme = movieRepository.save(movieEntity);
        } catch (OptimisticLockingFailureException olfe) {
            if(retryCount < maxRetry) {
                log.error("OptimisticLockingFailureException... retrying.");
                retryCount++;
                resme = movieRepository.save(movieEntity);
            }
        }
        return resme;
    }

    /**
     * get a movie by id
     *
     * @param id
     * @return
     */
    @Override
    public MovieEntity getMovie(Long id) throws DataNotFoundException {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if (movieEntityOptional.isPresent()) {
            return movieEntityOptional.get();
        } else {
            throw new DataNotFoundException(id, MovieEntity.class);
        }
    }

    /**
     * update a movie by id
     *
     * @param movieEntity
     * @return
     */
    @Override
    public MovieEntity update(MovieEntity movieEntity) throws DataNotFoundException {
        getMovie(movieEntity.getId());
        return addMovie(movieEntity);
    }

    /**
     * delete a movie by id
     *
     * @param id
     * @return
     */
    @Override
    public String delete(Long id) throws DataNotFoundException {
        MovieEntity movieEntity = getMovie(id);
        movieRepository.delete(movieEntity);
        return "Delete successful.";
    }
}
