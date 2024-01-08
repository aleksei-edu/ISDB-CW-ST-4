package com.pokeshop.pokemonshop.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class ExpiredJwtAdvice {
    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public String expiredJwtHandler(ExpiredJwtException ex) {
        return "Token expired";
    }
}
