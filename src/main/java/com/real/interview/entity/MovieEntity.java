package com.real.interview.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE")
public class MovieEntity {
    @Id
    @Generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;
    @ManyToMany
    private List<CastEntity> castEntities;


    @Version
    private Long version;
}