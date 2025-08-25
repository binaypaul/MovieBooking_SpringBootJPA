package com.real.interview.controller;

import com.real.interview.entity.MovieEntity;
import com.real.interview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to CRUD movies
 */
@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    private final MovieService movieServiceImpl;

    @Autowired
    public MovieController(MovieService movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    /**
     * Get all movies
     *
     * @param page   - page number
     * @param size   - size of each page
     * @param sortBy - field name to sort by
     * @param asc    - order by asc then true else false
     * @return movies - page of movies
     */
    @GetMapping("/getall")
    public ResponseEntity<Page<MovieEntity>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean asc
    ) {
        Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<MovieEntity> movieList = movieServiceImpl.getAll(pageable);
        return ResponseEntity.ok(movieList);
    }

    /**
     * Add a movie
     *
     * @param movie
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<MovieEntity> addMovie(@RequestBody MovieEntity movie) {
        MovieEntity status = movieServiceImpl.addMovie(movie);
        return ResponseEntity.ok(status);
    }
}