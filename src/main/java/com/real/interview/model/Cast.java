package com.real.interview.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cast {
    private long id;
    private String name;
    private int age;
    private Sex sex;
    private Address address;
    private List<Movie> movies;
}
