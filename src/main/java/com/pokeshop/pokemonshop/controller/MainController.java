package com.pokeshop.pokemonshop.controller;

import com.pokeshop.pokemonshop.DTO.*;
import com.pokeshop.pokemonshop.exception.UserNotFoundException;
import com.pokeshop.pokemonshop.model.*;
import com.pokeshop.pokemonshop.repository.*;
import com.pokeshop.pokemonshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @GetMapping("/entities")
    public ResponseEntity<List<Entities>> getEntities() {
        return ResponseEntity.ok(entitiesRepository.findAll());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = inStockRepository.findAll()
                .stream().map(inStock -> {
                    Entities entity = inStock.getEntity();
                    Pokemon pokemon = entity.getPokemon();
                    PokemonWiki pokemonWiki = pokemon.getPokemonWiki();
                    return ProductDTO.builder()
                            .id(entity.getId())
                            .name(pokemonWiki.getName())
                            .nationalNum(pokemonWiki.getNationalNum())
                            .pokemonImageLink(pokemonWiki.getPokemonImageLink())
                            .types(pokemonWiki.getPokemonTypes().stream().map(PokemonTypes::getName).toList())
                            .price(inStock.getPrice())
                            .quantity(inStock.getQuantity())
                            .build();
                }).toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user-info")
    public ResponseEntity<UseInfo> getUserInfo(
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        Trainers trainer = user.getTrainer();
        TrainerInfo trainerInfo = null;
        if (trainer != null) {
            trainerInfo = new TrainerInfo(
                    trainer.getFirstname(),
                    trainer.getLastname(),
                    trainer.getGender().toString(),
                    trainer.getLevel(),
                    trainer.getGameStyle().getPhysicalSweeper(),
                    trainer.getGameStyle().getSpecialSweeper(),
                    trainer.getGameStyle().getPhysicalTank(),
                    trainer.getGameStyle().getSpecialTank(),
                    trainer.getGameStyle().getWall()
            );
        }
        return ResponseEntity.ok(UseInfo.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .trainer(trainerInfo)
                .build());

    }

    @PutMapping("/update-trainer-info")
    public void updateUserInfo(
            Principal principal,
            @RequestBody TrainerInfo request
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        Trainers trainer = user.getTrainer();
        if (trainer == null) {
            trainer = new Trainers();
        }
        trainer.setFirstname(request.getFirstName());
        trainer.setLastname(request.getLastName());

        String genderStr = request.getGender();
        try {
            TrainersGender genderEnum = TrainersGender.valueOf(genderStr.toUpperCase());
            trainer.setGender(genderEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender value: " + genderStr);
        }
        trainer.setLevel(request.getLevel());
        if (trainer.getGameStyle() == null) {
            trainer.setGameStyle(new GameStylesDist());
        }
        if (request.getPhysicalSweeper() + request.getSpecialSweeper() + request.getPhysicalTank() + request.getSpecialTank() + request.getWall() != 100) {
            throw new IllegalArgumentException("Invalid game style distribution");
        }
        trainer.getGameStyle().setPhysicalSweeper(request.getPhysicalSweeper());
        trainer.getGameStyle().setSpecialSweeper(request.getSpecialSweeper());
        trainer.getGameStyle().setPhysicalTank(request.getPhysicalTank());
        trainer.getGameStyle().setSpecialTank(request.getSpecialTank());
        trainer.getGameStyle().setWall(request.getWall());
        user.setTrainer(trainer);
        userRepository.save(user);
    }


    @PostMapping("/checkout")
    public String checkout(
            Principal principal,
            @RequestBody List<CartItem> cartItems
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        Orders order = new Orders();
        int totalPrice = 0;
        Set<OrderItems> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            InStock inStock = inStockRepository.findById(cartItem.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + cartItem.getId()));
            if (inStock.getQuantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity for product: " + inStock.getEntity().getPokemon().getPokemonWiki().getName());
            }
            inStock.setQuantity(inStock.getQuantity() - cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItem.setItem(inStock.getEntity());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(inStock.getPrice());
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            orderItem.setOrder(order);
            inStockRepository.save(inStock);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatuses.PROCESSING);
        order.setUser(user);
        orderRepository.save(order);
        for (OrderItems orderItem : orderItems) {
            OrderItemsKey key = new OrderItemsKey();
            key.setOrderID(order.getId());
            key.setItemID(orderItem.getItem().getId());
            orderItem.setId(key);
            orderItemsRepository.save(orderItem);
        }

        return "Checkout successfully";
    }

    @GetMapping("/get-orders")
    public ResponseEntity<List<OrderDTO>> getOrders(
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<Orders> orders = user.getOrders();
        List<OrderDTO> response = orders.stream().map(order -> {
            List<ProductDTO> orderItems = order.getOrderItems().stream().map(orderItem -> {
                Entities entity = orderItem.getItem();
                Pokemon pokemon = entity.getPokemon();
                PokemonWiki pokemonWiki = pokemon.getPokemonWiki();
                return ProductDTO.builder()
                        .id(entity.getId())
                        .name(pokemonWiki.getName())
                        .nationalNum(pokemonWiki.getNationalNum())
                        .pokemonImageLink(pokemonWiki.getPokemonImageLink())
                        .types(pokemonWiki.getPokemonTypes().stream().map(PokemonTypes::getName).toList())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .build();
            }).toList();
            return OrderDTO.builder()
                    .id(order.getId())
                    .status(order.getStatus())
                    .totalPrice(order.getTotalPrice())
                    .orderDate(order.getOrderDate())
                    .orderItems(orderItems)
                    .build();
        }).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("pokemon-details/{id}")
    public ResponseEntity<PokemonDetails> getPokemonDetails(
            @PathVariable int id
    ) {
        Entities entity = entitiesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pokemon id: " + id));
        Pokemon pokemon = entity.getPokemon();
        PokemonWiki pokemonWiki = pokemon.getPokemonWiki();
        InStock inStock = inStockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pokemon id: " + id));
        return ResponseEntity.ok(PokemonDetails.builder()
                .id(entity.getId())
                .name(pokemonWiki.getName())
                .nationalNum(pokemonWiki.getNationalNum())
                .pokemonImageLink(pokemonWiki.getPokemonImageLink())
                .types(pokemonWiki.getPokemonTypes().stream().map(PokemonTypes::getName).toList())
                .species(pokemonWiki.getSpecies())
                .height(pokemonWiki.getHeight())
                .weight(pokemonWiki.getWeight())
                .hp(pokemonWiki.getBaseStats().getHp())
                .attack(pokemonWiki.getBaseStats().getAttack())
                .defense(pokemonWiki.getBaseStats().getDefense())
                .specialAttack(pokemonWiki.getBaseStats().getSpAttack())
                .specialDefense(pokemonWiki.getBaseStats().getSpDefense())
                .speed(pokemonWiki.getBaseStats().getSpeed())
                .price(inStock.getPrice())
                .quantity(inStock.getQuantity())
                .build());

    }


    @GetMapping("/stonewiki")
    public ResponseEntity<List<StoneWiki>> getStoneWiki() {
        return ResponseEntity.ok(stoneWikiRepository.findAll());
    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        return ResponseEntity.ok().body("File uploaded successfully: " + storageService.uploadFile(file));
//    }
//
//    @GetMapping("/{filename}")
//    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
//        FileData tmp = storageService.dowloadFile(filename);
//        return ResponseEntity.ok().contentType(MediaType.valueOf(tmp.getFileType())).
//                body(tmp.getData());
//    }

}
