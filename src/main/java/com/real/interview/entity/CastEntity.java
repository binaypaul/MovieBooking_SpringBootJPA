package com.real.interview.entity;

import com.real.interview.model.Sex;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON")
public class CastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
    @ManyToMany(mappedBy = "castEntities")
    private List<MovieEntity> movieEntities;


    @Version
    private Long version;
}
