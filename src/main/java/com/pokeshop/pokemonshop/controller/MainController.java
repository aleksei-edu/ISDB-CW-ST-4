package com.pokeshop.pokemonshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pokeshop.pokemonshop.model.Entities;
import com.pokeshop.pokemonshop.model.WikiType;
import com.pokeshop.pokemonshop.repository.EntitiesRepo;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {
    @Autowired
    private EntitiesRepo entitiesRepo;

    @GetMapping("/entities")
    public ResponseEntity<List<Entities>> getEntities() {
        return ResponseEntity.ok(entitiesRepo.findAll());
    }

}
