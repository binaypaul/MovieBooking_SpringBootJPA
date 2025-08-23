package com.real.interview.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private long id;
    private String name;
    private int rating;
    private Genre genre;
    private List<Cast> casts;
}