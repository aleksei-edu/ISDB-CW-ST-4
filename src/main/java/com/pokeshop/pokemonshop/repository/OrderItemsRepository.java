package com.pokeshop.pokemonshop.repository;


import com.pokeshop.pokemonshop.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer>{
}
