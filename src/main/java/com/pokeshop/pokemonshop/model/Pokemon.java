package com.pokeshop.pokemonshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @Column(name = "entityid")
    private int id;
    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "entityid")
    private Entities entity;

    @OneToOne
    @JoinColumn(name = "pokemonwikiid", referencedColumnName = "id")
    private PokemonWiki pokemonWiki;

    @Column(name = "level")
    private int level;
}
