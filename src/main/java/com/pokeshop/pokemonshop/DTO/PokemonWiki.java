package com.pokeshop.pokemonshop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonWiki {
    @NotNull
    int id;
    String name;
    int nationalNum;
    String species;
    float height;
    float weight;
    BaseStats baseStats;
}
