package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.InStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InStockRepository extends JpaRepository<InStock, Integer> {
}
