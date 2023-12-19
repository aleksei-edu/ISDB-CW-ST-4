package com.pokeshop.pokemonshop.model;

import jakarta.persistence.Entity;
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
@Table(name="pokemontypes")
public class PokemonTypes {
    @Id
    @NotNull
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "pokemonTypes")
    Set<PokemonWiki> pokemonWikis;

}
