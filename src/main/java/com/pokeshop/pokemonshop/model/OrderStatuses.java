package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

public enum OrderStatuses {
    PROCESSING, DONE, PAID, CANCELLED

}
