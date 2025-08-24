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
    private String type;
    @OneToMany(mappedBy = "genreEntity", cascade = CascadeType.ALL)
    private List<MovieEntity> movieEntities;


    @Version
    private Long version;
}
