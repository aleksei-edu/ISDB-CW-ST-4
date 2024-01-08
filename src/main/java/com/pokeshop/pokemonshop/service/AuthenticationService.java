package com.pokeshop.pokemonshop.service;

import com.pokeshop.pokemonshop.DTO.AuthenticationRequest;
import com.pokeshop.pokemonshop.DTO.AuthenticationResponse;
import com.pokeshop.pokemonshop.DTO.RegisterRequest;
import com.pokeshop.pokemonshop.config.JwtService;
import com.pokeshop.pokemonshop.exception.UserAlreadyExistsException;
import com.pokeshop.pokemonshop.model.Role;
import com.pokeshop.pokemonshop.model.User;
import com.pokeshop.pokemonshop.repository.UserRepository;
import com.pokeshop.pokemonshop.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        repository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(user.getUsername());
                });
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(()->new UserNotFoundException(request.getUsername()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}