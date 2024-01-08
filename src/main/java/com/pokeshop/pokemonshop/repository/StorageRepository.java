package com.pokeshop.pokemonshop.repository;

import com.pokeshop.pokemonshop.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<FileData, Long> {


    Optional<FileData> findByFileName(String fileName);

}
