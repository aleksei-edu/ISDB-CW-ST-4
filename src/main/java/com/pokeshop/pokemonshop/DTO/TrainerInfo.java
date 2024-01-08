package com.pokeshop.pokemonshop.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerInfo {
    private String firstName;
    private String lastName;
    private String gender;
    int level;
    private int physicalSweeper;
    private int specialSweeper;
    private int wall;
    private int physicalTank;
    private int specialTank;

}
