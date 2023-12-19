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
@Table(name="gamestylesdist")
public class GameStylesDist {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="physicalsweeper")
    private int physicalSweeper;

    @Column(name="specialsweeper")
    private int specialSweeper;

    @Column(name="wall")
    private int wall;

    @Column(name="physicaltank")
    private int physicalTank;

    @Column(name="specialtank")
    private int specialTank;

    @OneToOne(mappedBy = "gameStyle")
    Trainers trainer;
}
