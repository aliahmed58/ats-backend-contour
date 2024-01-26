package com.training.ats.services;

import com.training.ats.auth.RegisterRequest;
import com.training.ats.repositories.AtsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

/**
 * service module responsible for the business logic of authenticating users
 */

@Service
public class AuthService {

    @Autowired
    private AtsUserRepository userRepository;
    /**
     * Method responsible for registering a new user
     * @param registerRequest register request body
     * @return jwt token string
     */
    public String registerUser(RegisterRequest registerRequest) throws AuthenticationException {
        // check if user already exists or not
        if (userRepository.findById(registerRequest.username()).isPresent()) {
            throw new AuthenticationException("User already exists");
        }
        else {
            return null;
        }
    }
}
