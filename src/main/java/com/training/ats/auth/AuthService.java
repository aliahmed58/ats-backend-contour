package com.training.ats.auth;

import com.training.ats.auth.AuthRequest;
import com.training.ats.auth.AuthResponse;
import com.training.ats.auth.JwtService;
import com.training.ats.auth.RegisterRequest;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

/**
 * service module responsible for the business logic of authenticating users
 */

@Service
public class AuthService {

    @Autowired
    private AtsUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * Method responsible for registering a new user
     * @param registerRequest register request body
     * @return jwt token string
     */
    public AuthResponse registerUser(RegisterRequest registerRequest) throws AuthenticationException {
        // check if user already exists or not
        if (userRepository.findById(registerRequest.username()).isPresent()) {
            throw new AuthenticationException("User already exists");
        }
        // check if password and confirm passwords match
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new AuthenticationException("Password and Confirm password do not match");
        }

        // create a new user object to save in repository
        AtsUser user = AtsUser.builder()
                .username(registerRequest.username())
                .passwordHash(passwordEncoder.encode(registerRequest.password()))
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .roleType(RoleType.APPLICANT)
                .build();
        // persist user in database
        userRepository.save(user);
        // generate jwt token
        return new AuthResponse(jwtService.generateJwt(user));
    }

    public AuthResponse authenticateUser(AuthRequest request) {
        // authenticate the user using the authentication manager
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        // get user from database, create token and return
        AtsUser user = userRepository.findById(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new AuthResponse(jwtService.generateJwt(user));
    }
}
