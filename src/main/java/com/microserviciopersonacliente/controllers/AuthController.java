package com.microserviciopersonacliente.controllers;

import com.microserviciopersonacliente.entities.User;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;

@RestController
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Aquí podrías realizar la autenticación del usuario
        // Si las credenciales son válidas, genera un token JWT y devuélvelo
        String token = JwtService.generateToken(user.getUsername());
        return token;
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestBody String token) {
        try {
            Claims claims = JwtService.parseToken(token);
            // Puedes realizar verificaciones adicionales aquí si es necesario
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

