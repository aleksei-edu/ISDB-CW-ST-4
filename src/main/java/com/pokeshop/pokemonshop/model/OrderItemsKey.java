package com.pokeshop.pokemonshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data

@Embeddable
public class OrderItemsKey implements Serializable {
    @Column(name = "orderid")
    private int orderID;

    @Column(name = "itemid")
    private int itemID;
}
