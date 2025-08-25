package com.real.interview.exception;

import com.real.interview.entity.MovieEntity;

public class DataNotFoundException extends Exception {
    public DataNotFoundException(Long id, Class<MovieEntity> movieEntityClass) {
        super(movieEntityClass.getName() + "not found corresponding to id " + id);
    }
}
