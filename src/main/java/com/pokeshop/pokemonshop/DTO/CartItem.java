package com.pokeshop.pokemonshop.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    int id;
    int quantity;
}
