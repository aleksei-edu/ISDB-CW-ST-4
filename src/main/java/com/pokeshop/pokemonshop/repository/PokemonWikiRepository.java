package com.pokeshop.pokemonshop.repository;
import com.pokeshop.pokemonshop.model.PokemonWiki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonWikiRepository extends JpaRepository<PokemonWiki, Integer> {
}
