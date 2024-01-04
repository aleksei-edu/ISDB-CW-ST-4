package com.pokeshop.pokemonshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Id;
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
@Table(name = "pokemonwiki")
public class PokemonWiki {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "nationalnum")
    private int nationalNum;

    @Column(name = "species")
    private String species;

    @Column(name = "height")
    private float height;

    @Column(name = "weight")
    private float weight;

    @Column(name = "pokemonimagelink")
    private String pokemonImageLink;

    @OneToOne
    @JoinColumn(name = "basestatsid", referencedColumnName = "id")
    private BaseStats baseStats;

//    @JsonIgnore
//    @OneToOne(mappedBy = "pokemonWiki")
//    Pokemon pokemon;

    @ManyToMany
    @JoinTable(
            name = "pokemontotypes",
            joinColumns = @JoinColumn(name = "pokemonid"),
            inverseJoinColumns = @JoinColumn(name = "pokemontypesid"))
    Set<PokemonTypes> pokemonTypes;

//    @JsonIgnore
//    @OneToMany(mappedBy = "evolvingFrom")
//    Set<EvolutionChains> evolvingFrom;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "evolvingTo")
//    Set<EvolutionChains> evolvingTo;

}
