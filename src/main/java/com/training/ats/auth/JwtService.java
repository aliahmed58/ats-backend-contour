package com.training.ats.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * util service to manipulate and use JWTs for example extracting username
 */

@Service
public class JwtService {

  private static final String SECRET_KEY = "3dVTE8cmOFZvRvYMigGtdCGfPcdUrnTTCA999CCDA674B";

  /**
   * @param jwt json web token
   * @return the username
   */
  public String extractUsername(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  /**
   * get expiration from claims
   * @param jwt json web token string
   * @return Date object for the expiration of given jwt
   */
  private Date extractExpiration(String jwt) {
    return extractClaim(jwt, Claims::getExpiration);
  }

  private boolean isJwtExpired(String jwt) {
    final Date date = extractExpiration(jwt);
    return date.before(new Date());
  }

  /**
   * Generate a token for the given user details
   *
   * @param userDetails user details object for the user
   * @return json web token
   */
  public String generateJwt(UserDetails userDetails) {
    Map<String, Object> claims = userDetails.getAuthorities()
            .stream()
            .collect(Collectors.toMap(GrantedAuthority::getAuthority, s -> true));
    return generateJwt(claims, userDetails);
  }

  public String generateRefreshJwt(UserDetails userDetails) {
    Map<String, Object> claims = userDetails.getAuthorities()
            .stream()
            .collect(Collectors.toMap(GrantedAuthority::getAuthority, s -> true));
    return generateRefreshJwt(claims, userDetails);
  }

  /**
   * Generate a new token given the claims and user details object
   * @param extraClaims the map of claims that should be added
   * @param userDetails the User details object of the user
   * @return json web token for the given claims and user details
   */
  public String generateJwt(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
        .builder()
        .claims(extraClaims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
        .signWith(getVerifyKey())
        .compact();
  }

  public String generateRefreshJwt(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 604800000)) // 7 days
            .signWith(getVerifyKey())
            .compact();
  }

  /**
   * Check if the given jwt is valid
   * @param jwt json web token string
   * @param userDetails user details object
   * @return true if valid, else false
   */
  public boolean isJwtValid(String jwt, UserDetails userDetails) {
    final String username = extractUsername(jwt);
    return (username.equals(userDetails.getUsername())) && !isJwtExpired(jwt);
  }

  /**
   * Extracts claims from the jwt token using a function as a parameter
   * @param jwt token
   * @param claimResolver a function from type Claims returning R
   * @return R the claim extracted
   * @param <R> the return type value
   */
  public <R> R extractClaim(String jwt, Function<Claims, R> claimResolver) {
    final Claims claims = extractClaims(jwt);
    return claimResolver.apply(claims);
  }

  /**
   * method extracting claims from the jwt using Jwt parser
   * @param jwt json web token string
   * @return Claims object containing all claims
   */
  private Claims extractClaims(String jwt) {
    return Jwts
        .parser()
        .verifyWith(getVerifyKey())
        .build()
        .parseSignedClaims(jwt)
        .getPayload();
  }

  /**
   * Get verify key from the secret key stored
   * @return Secret Key object
   */
  private SecretKey getVerifyKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
