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
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currencyfull")
    private String currencyFull;

    @Column(name = "currencyshort")
    private String currencyShort;

    public Currency(Long id, String currencyFull, String currencyShort) {
        this.id = id;
        this.currencyFull = currencyFull;
        this.currencyShort = currencyShort;
    }


}