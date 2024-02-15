package com.training.ats.auth;

import com.training.ats.auth.AuthRequest;
import com.training.ats.auth.AuthResponse;
import com.training.ats.auth.JwtService;
import com.training.ats.auth.RegisterRequest;
import com.training.ats.exceptions.ErrorMessageBuilder;
import com.training.ats.exceptions.ErrorType;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Arrays;
import java.util.Optional;

/**
 * service module responsible for the business logic of authenticating users
 */

@Service
public class AuthService {

    private static final String LOGGER = "Auth service";

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
    public AuthResponse registerUser(RegisterRequest registerRequest, HttpServletResponse response) throws AuthenticationException {
        // check if user already exists or not
        if (userRepository.findById(registerRequest.username()).isPresent()) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_EXISTS));
        }
        // check if password and confirm passwords match
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.PASS_DO_NOT_MATCH));
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
        String accessToken = jwtService.generateJwt(user);
        String refreshToken = jwtService.generateRefreshJwt(user);
        Cookie c = new Cookie("jwt", refreshToken);
        c.setHttpOnly(true);
        c.setPath("/");
        Cookie userCookie = new Cookie("user", user.getUsername());
        response.addCookie(userCookie);
        response.addCookie(c);
        return new AuthResponse(accessToken);
    }

    public AuthResponse authenticateUser(AuthRequest request, HttpServletResponse response) {
        // authenticate the user using the authentication manager
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        // get user from database, create token and return
        AtsUser user = userRepository.findById(request.username())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND)));

        String accessToken = jwtService.generateJwt(user);
        String refreshToken = jwtService.generateRefreshJwt(user);
        Cookie jwtCookie = new Cookie("jwt", refreshToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return new AuthResponse(accessToken);
    }

    public AuthResponse refreshToken(HttpServletRequest request,
                                     HttpServletResponse response) throws AuthenticationException {
        // get cookies
        if (request.getCookies() == null) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }
        Optional<Cookie> refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("jwt"))
                .findAny();
        if (refreshTokenCookie.isEmpty()) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }
        Cookie cookie = refreshTokenCookie.get();
        String refreshToken = cookie.getValue();
        String username = jwtService.extractUsername(refreshToken);
        // get user from database, create token and return
        AtsUser user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND)));
        if (jwtService.isJwtValid(refreshToken, user)) {
            String accessToken = jwtService.generateJwt(user);
            return new AuthResponse(accessToken);
        }
        else {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // logout user by deleting the refresh token from cookie
        // get cookies
        if (request.getCookies() == null) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }
        Optional<Cookie> refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("jwt"))
                .findAny();
        if (refreshTokenCookie.isEmpty()) {
            throw new AuthenticationException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }

        Cookie changedCookie = new Cookie("jwt", "invalid");
        changedCookie.setMaxAge(0);
        changedCookie.setHttpOnly(true);
        changedCookie.setPath("/");
        response.addCookie(changedCookie);
        return "Logged out";
    }
}
