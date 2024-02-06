package com.training.ats.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * a component that extends from the OncePerRequestFilter basically applying a Filter only once
 * to any server request made, in this we can access the request body, modify the response
 * sort of equivalent to express middlewares
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  /*
  The request header will carry the token in the format
  Authorization: Bearer <jwt token here>
   */

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    if (request.getRequestURI().contains("register") || request.getRequestURI().contains("authenticate")) {
      filterChain.doFilter(request, response);
      return;
    }
    if (request.getCookies() == null) {
      filterChain.doFilter(request, response);
      return;
    }
    // get the cookie from http only cookie
    // find the cookie from cookies using stream
    Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(m -> m.getName().equals("jwt")).findFirst();
    if (cookie.isEmpty()) {
      filterChain.doFilter(request, response);
      return;
    }
    String jwt = cookie.get().getValue();
    // get username from jwt token
    String username = jwtService.extractUsername(jwt);

    // check if username is not null AND user is already not authenticated
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // create a UserDetails object using the service in the app config
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      // check if the jwt is valid - jwt is verified, username is fetched, and it's not expired
      if (jwtService.isJwtValid(jwt, userDetails)) {

        // create a username password authentication token for spring security
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        // add details of web authentication using the request
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // set new authentication for the current user
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    // move to the next filter chain
    filterChain.doFilter(request, response);
  }
}
