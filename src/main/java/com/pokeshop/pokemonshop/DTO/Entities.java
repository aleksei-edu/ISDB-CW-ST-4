package com.pokeshop.pokemonshop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Entities {
    @NotNull
    int id;
    @NotNull
    WikiType wikiType;
}
