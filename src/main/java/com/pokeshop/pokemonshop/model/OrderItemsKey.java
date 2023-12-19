package com.pokeshop.pokemonshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemsKey implements Serializable {
    @Column(name = "orderid")
    private int orderID;

    @Column(name = "itemid")
    private int itemID;
}
