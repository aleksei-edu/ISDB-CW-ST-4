package com.pokeshop.pokemonshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="basestats")
public class BaseStats {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name="hp")
    private int hp;

    @NotNull
    @Column(name="attack")
    private int attack;

    @NotNull
    @Column(name="defense")
    private int defense;

    @NotNull
    @Column(name="spattack")
    private int spAttack;

    @NotNull
    @Column(name="spdefense")
    private int spDefense;

    @NotNull
    @Column(name="speed")
    private int speed;

//    @JsonIgnore
//    @OneToOne(mappedBy = "baseStats")
//    private PokemonWiki pokemonWiki;

}
