package com.pokeshop.pokemonshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instock")
public class InStock {
    @Id
    @Column(name = "entityid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "entityid")
    private Entities entity;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

}
