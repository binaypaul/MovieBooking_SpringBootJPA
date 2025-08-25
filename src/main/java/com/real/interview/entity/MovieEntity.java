package com.real.interview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.real.interview.model.Review;
import jakarta.persistence.*;
import java.util.ArrayList;
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
    private Long id;
    private String name;
    private Integer rating;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id", referencedColumnName = "id")
    )
    private List<CastEntity> castEntities;

    @OneToMany(mappedBy = "movieEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewEntity> reviewEntities;

    // Add this method to handle the bidirectional relationship
//    public void addReview(ReviewEntity reviewEntity) {
//        if (reviewEntities == null) {
//            reviewEntities = new ArrayList<>();
//        }
//        reviewEntities.add(reviewEntity);
//        reviewEntity.setMovieEntity(this);
//    }

    @Version
    private Long version;
}