package com.real.interview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    @OneToOne(mappedBy = "addressEntity", cascade = CascadeType.ALL)
    private CastEntity castEntity;


//    @Version
//    private Long version;
}
