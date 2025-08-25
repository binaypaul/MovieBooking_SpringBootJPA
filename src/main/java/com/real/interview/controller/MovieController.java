package com.real.interview.controller;

import com.real.interview.entity.MovieEntity;
import com.real.interview.exception.DataNotFoundException;
import com.real.interview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
     * @param movieEntity
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<MovieEntity> create(@RequestBody MovieEntity movieEntity) {
        MovieEntity status = movieServiceImpl.addMovie(movieEntity);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    /**
     * get a movie by id
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<MovieEntity> get(@PathVariable("id") Long id)
            throws DataNotFoundException {
        MovieEntity status = movieServiceImpl.getMovie(id);
        return ResponseEntity.ok(status);
    }

    /**
     * update a movie by id
     *
     * @param movieEntity
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<MovieEntity> update(@RequestBody MovieEntity movieEntity) throws DataNotFoundException {
        MovieEntity status = movieServiceImpl.update(movieEntity);
        return ResponseEntity.ok(status);
    }

    /**
     * delete a movie by id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws DataNotFoundException {
        String status = movieServiceImpl.delete(id);
        return ResponseEntity.ok(status);
    }
}