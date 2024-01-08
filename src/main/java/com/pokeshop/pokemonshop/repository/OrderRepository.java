package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer>
{
}
