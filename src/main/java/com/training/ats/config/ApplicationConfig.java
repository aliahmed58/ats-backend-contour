package com.training.ats.config;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class ApplicationConfig {

    @Autowired
    private AtsUserRepository repository;

    /**
     * defines where users will be looked for during auth in the application
     * @return user details service looking for user in database by username
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // ad a sample recruiter
        repository.save(AtsUser.builder()
                .username("test1").firstName("John").lastName("Doe")
                .passwordHash(passwordEncoder().encode("test")).roleType(RoleType.RECRUITER).build());
        return username -> repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    /**
     * Responsible for managing authentication
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Creating a bean of custom authentication provider, in charge of setting which user details service to use
     * and which password encoder to use
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * return a bean of password encoder used to hash passwords
     * @return password encoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
