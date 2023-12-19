package com.pokeshop.pokemonshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EvolutionChainsKey implements Serializable {
    @Column(name = "evolvingfromid")
    private int evolvingFromID;

    @Column(name = "evolvingtoid")
    private int evolvingToID;



}
