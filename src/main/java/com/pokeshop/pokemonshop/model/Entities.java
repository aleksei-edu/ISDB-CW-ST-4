package com.pokeshop.pokemonshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="entities")
public class Entities {
    @Id
    @NotNull
    private int id;

    @Column(name="wikitype")
    @Enumerated(EnumType.STRING)
    private WikiTypes wikiType;

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Pokemon pokemon;

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private StoneWiki stoneWiki;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "entities")
//    Set<Trainers> trainers;

}
