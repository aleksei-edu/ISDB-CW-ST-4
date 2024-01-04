package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
