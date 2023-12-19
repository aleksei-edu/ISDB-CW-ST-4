package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.Entities;
import com.pokeshop.pokemonshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
