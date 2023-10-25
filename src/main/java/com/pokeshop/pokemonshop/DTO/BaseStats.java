package com.pokeshop.pokemonshop.DTO;

import jakarta.validation.constraints.NotNull;

public class BaseStats {
    @NotNull
    int id;
    @NotNull
    int hp;
    @NotNull
    int attack;
    @NotNull
    int defense;
    @NotNull
    int spAttack;
    @NotNull
    int spDefense;
    @NotNull
    int speed;
}
