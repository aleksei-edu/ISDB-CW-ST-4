package com.pokeshop.pokemonshop.DTO;

import com.pokeshop.pokemonshop.model.Trainers;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UseInfo {
    private String username;
    private String avatar;

    private TrainerInfo trainer;
}
