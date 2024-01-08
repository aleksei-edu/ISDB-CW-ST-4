package com.pokeshop.pokemonshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private int nationalNum;
    private String pokemonImageLink;
    private List<String> types;
    private int price;
    private int quantity;
}
