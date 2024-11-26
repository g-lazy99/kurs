package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orderveriety")
public class OrderVeriety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String veriety;

    public OrderVeriety(Long id, String veriety) {
        this.id = id;
        this.veriety = veriety;
    }


}