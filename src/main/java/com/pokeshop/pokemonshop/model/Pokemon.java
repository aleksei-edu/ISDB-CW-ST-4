package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;


@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @Column(name = "entityid")
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "entityid")
    private Entities entity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemonwikiid", referencedColumnName = "id")
    private PokemonWiki pokemonWiki;
    @Column(name = "level")
    private int level;
}
