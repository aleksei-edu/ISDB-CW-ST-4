package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="stonewiki")
public class StoneWiki {
    @Id
    @Column(name = "entityid")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "entityid")
    private Entities entity;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "stoneWiki")
    Set<EvolutionChains> evolutionChains;
}
