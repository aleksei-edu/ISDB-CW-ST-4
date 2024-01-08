package com.pokeshop.pokemonshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filedata")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    @Column(name="data")
    private byte[] data;

}
