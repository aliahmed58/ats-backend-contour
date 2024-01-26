package com.training.ats.config;

import com.training.ats.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring security uses this config class to make beans of security filter chain.
 * filter chain is a chain of filters responsible for adding or removing filters to requests
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * Set security filter chain for http security
     * 1. disable csrf (cross site request forgery)
     * 2. allow login, register endpoints and keep all other endpoints locked
     * 3. set session management to be stateless since we're using jwt
     * 4. set the auth provider of custom implementation
     * 5. add the jwt filter before
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // same as AbstractHttpConfigurer config -> config.disable()
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/register", "/authenticate")
                            .permitAll()
                            .anyRequest()
                            .authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                ;
        return httpSecurity.build();
    }

}
