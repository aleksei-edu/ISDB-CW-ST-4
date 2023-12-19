package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitiesRepo extends JpaRepository<Entities, Long> {

}
