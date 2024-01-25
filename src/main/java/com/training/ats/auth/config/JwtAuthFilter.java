package com.training.ats.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
    // get the jwt token from the request header
    final String authHeader = request.getHeader("Authorization");
    // filter and return if jwt not found
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    // get jwt after Bearer
    final String jwt = authHeader.split(" ")[1];
    String username = jwtService.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    }
  }
}
