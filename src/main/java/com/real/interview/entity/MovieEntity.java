package com.real.interview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE")
@SequenceGenerator(name = "seqGen", sequenceName = "movieIdSeq", allocationSize = 1)
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @Column(columnDefinition = "BIGINT CHECK (id <= 999999)")
    private Long id;
    private String name;
    private Integer rating;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id", referencedColumnName = "id")
    )
    private List<CastEntity> casts;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewEntity> reviews;

    @Version
    private Long version;
}