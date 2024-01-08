package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Orders {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    User user;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    OrderStatuses status;

    @Column(name="totalprice")
    int totalPrice;

    @Column(name="orderdate")
    Date orderDate;

    @OneToMany(mappedBy = "order")
    Set<OrderItems> orderItems;
}
