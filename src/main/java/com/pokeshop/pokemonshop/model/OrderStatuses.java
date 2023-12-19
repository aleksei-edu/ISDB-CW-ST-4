package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="orderstatuses")
public class OrderStatuses {
    @Id
    @Column(name="id")
    int id;

    @NotNull
    @Column(name="name")
    String name;

    @OneToMany(mappedBy = "status")
    Set<Orders> orders;

}
