package com.pokeshop.pokemonshop.model;

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
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entityid", referencedColumnName = "id")
    private Entities entity;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "item")
    private Set<OrderItems> orderItems;
}
