package com.pokeshop.pokemonshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "entityid")
    private Entities entity;

    @Column(name = "name")
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "stoneWiki")
    Set<EvolutionChains> evolutionChains;
}
