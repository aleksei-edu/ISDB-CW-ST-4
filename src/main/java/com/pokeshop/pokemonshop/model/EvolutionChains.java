package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="evolutionchains")
public class EvolutionChains {
    @EmbeddedId
    private EvolutionChainsKey id;

    @ManyToOne
    @MapsId("evolvingFromID")
    @JoinColumn(name = "evolvingfromid")
    private PokemonWiki evolvingFrom;

    @ManyToOne
    @MapsId("evolvingToID")
    @JoinColumn(name = "evolvingtoid")
    private PokemonWiki evolvingTo;

    @ManyToOne
    @JoinColumn(name = "stoneid")
    private StoneWiki stoneWiki;

}
