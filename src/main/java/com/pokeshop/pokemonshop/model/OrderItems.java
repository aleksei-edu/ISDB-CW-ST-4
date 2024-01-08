package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orderitems")
public class OrderItems {
    @EmbeddedId
    private OrderItemsKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderid")
    private Orders order;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "itemid")
    private Entities item;

    private int quantity;

    private int price;
}
