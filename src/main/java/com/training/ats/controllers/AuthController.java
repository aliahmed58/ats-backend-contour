package com.training.ats.controllers;

import com.training.ats.auth.AuthRequest;
import com.training.ats.auth.AuthResponse;
import com.training.ats.auth.RegisterRequest;
import com.training.ats.services.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest registerRequest) throws AuthenticationException {
        System.out.println(registerRequest);
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticateUser(authRequest));
    }
}
