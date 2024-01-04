package com.pokeshop.pokemonshop.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String username) {
        super("The user with username " + username + " already exists");
    }
}
