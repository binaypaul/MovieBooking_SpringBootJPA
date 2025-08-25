package com.real.interview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "CASTS")
@TableGenerator(
        name = "tabGen",
        table = "castIdGen",
        pkColumnName = "castIdPk",
        valueColumnName = "nextValue",
        pkColumnValue =  "id",
        allocationSize = 1
)
public class CastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabGen")
    private Long id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addressEntity;

    @ManyToMany(mappedBy = "castEntities", cascade = CascadeType.ALL)
    private List<MovieEntity> movieEntities;


    @Version
    private Long version;
}
