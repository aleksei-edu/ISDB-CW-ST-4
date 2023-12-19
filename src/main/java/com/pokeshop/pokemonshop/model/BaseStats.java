package com.pokeshop.pokemonshop.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BaseStats {
    @jakarta.persistence.Id
    @Id
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
