package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trainers")
public class Trainers {
    @Id
    @Column(name="id")
    int id;

    @Column(name="firstname")
    String firstname;

    @Column(name="lastname")
    String lastname;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    TrainersGender gender;

    @Column(name="level")
    int level;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gamestyleid", referencedColumnName = "id")
    GameStylesDist gameStyle;

    @ManyToMany
    @JoinTable(
            name = "trainersentities",
            joinColumns = @JoinColumn(name = "trainerid"),
            inverseJoinColumns = @JoinColumn(name = "entityid"))
    Set<Entities> entities;

    @OneToMany(mappedBy = "trainer")
    Set<Orders> orders;

    @OneToOne(mappedBy = "trainer")
    private User user;
}
