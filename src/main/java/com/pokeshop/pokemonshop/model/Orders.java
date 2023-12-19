package com.pokeshop.pokemonshop.model;

import java.util.Date;

public class Orders {
    int id;
    Trainers trainer;
    OrderStatuses status;
    int totalPrice;
    Date orderDate;
}
