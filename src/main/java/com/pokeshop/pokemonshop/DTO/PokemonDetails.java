package com.pokeshop.pokemonshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDetails {
    private int id;
    private String name;
    private int nationalNum;
    private String pokemonImageLink;
    private List<String> types;
    private String species;
    private float height;
    private float weight;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;


    private int price;
    private int quantity;

}
