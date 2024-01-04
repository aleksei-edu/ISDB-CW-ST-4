package com.pokeshop.pokemonshop.controller;
import com.pokeshop.pokemonshop.model.*;
import com.pokeshop.pokemonshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {
    @Autowired
    private EntitiesRepository entitiesRepository;
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PokemonWikiRepository pokemonWikiRepository;
    @Autowired
    private StoneWikiRepository stoneWikiRepository;
    @Autowired
    private InStockRepository inStockRepository;

    @GetMapping("/entities")
    public ResponseEntity<List<Entities>> getEntities() {
        return ResponseEntity.ok(entitiesRepository.findAll());
    }

    @GetMapping("/products")
    public ResponseEntity<List<InStock>> getProducts() {
        List<InStock> products = inStockRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stonewiki")
    public ResponseEntity<List<StoneWiki>> getStoneWiki() {
        return ResponseEntity.ok(stoneWikiRepository.findAll());
    }

}
