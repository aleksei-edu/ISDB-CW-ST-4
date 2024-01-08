package com.pokeshop.pokemonshop.DTO;

import com.pokeshop.pokemonshop.model.OrderStatuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private OrderStatuses status;
    private int totalPrice;
    private Date orderDate;
    private List<ProductDTO> orderItems;
}
