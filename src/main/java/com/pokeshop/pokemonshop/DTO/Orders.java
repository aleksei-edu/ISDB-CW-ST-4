package com.pokeshop.pokemonshop.DTO;

import java.util.Date;

public class Orders {
    int id;
    Trainers trainer;
    OrderStatuses status;
    int totalPrice;
    Date orderDate;
}
