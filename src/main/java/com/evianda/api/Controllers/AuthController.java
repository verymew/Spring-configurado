package com.evianda.api.Controllers;

import com.evianda.api.Dtos.UserDto;
import com.evianda.api.Models.User;
import com.evianda.api.Repositories.UserRepository;
import com.evianda.api.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.naming.AuthenticationException;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/message")
    String nome(){
        return "oii";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user){
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(user.username(), user.password());
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);
            String token = tokenService.generateToken(authenticationResponse);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inválido");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user){
        User novo = new User();
        String criptpass = passwordEncoder.encode(user.password());
        novo.setPassword(criptpass);
        novo.setUsername(user.username());
        try {
            userRepository.save(novo);
            return ResponseEntity.ok(novo);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não registrado" + e);
        }
    }
}
