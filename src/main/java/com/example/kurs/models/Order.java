package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordertypeid", nullable = false)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "orderverietyid", nullable = false)
    private OrderVeriety orderVeriety;

    @ManyToOne
    @JoinColumn(name = "currencyid", nullable = false)
    private Currency currency;

    private String tiker;
    private Integer number;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    private String duration;
    private Integer count;

}
