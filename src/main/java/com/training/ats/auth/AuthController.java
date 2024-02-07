package com.training.ats.auth;

import com.training.ats.auth.AuthRequest;
import com.training.ats.auth.AuthResponse;
import com.training.ats.auth.RegisterRequest;
import com.training.ats.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * registering a user endpoint
     * @param registerRequest request body containing user data
     * @return jwt token as json - AuthResponse object
     * @throws AuthenticationException if user already exists, returns with an exception and 409 code
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid RegisterRequest registerRequest, HttpServletResponse response)
        throws AuthenticationException {
        return ResponseEntity.ok(authService.registerUser(registerRequest, response));
    }

    /**
     * authenticating an already existing user
     * @param authRequest request body containing username password
     * @return jwt token - AuthResponse object
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Valid AuthRequest authRequest, HttpServletResponse response) {
        return ResponseEntity.ok(authService.authenticateUser(authRequest, response));
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response) throws AuthenticationException {
        return ResponseEntity.ok(authService.refreshToken(request, response));
    }

}
