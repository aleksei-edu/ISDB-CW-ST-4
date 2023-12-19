package com.pokeshop.pokemonshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonWiki {
    @Id
    Long id;
    String name;
    int nationalNum;
    String species;
    float height;
    float weight;
    BaseStats baseStats;
}
