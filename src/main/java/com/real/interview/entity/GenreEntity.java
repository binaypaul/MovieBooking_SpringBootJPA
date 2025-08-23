package com.real.interview.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GENRE")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    @OneToMany(mappedBy = "genreEntity")
    private List<MovieEntity> movieEntities;


    @Version
    private Long version;
}
