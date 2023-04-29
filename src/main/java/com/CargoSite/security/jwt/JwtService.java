package com.CargoSite.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.CargoSite.models.AppUser;
import com.CargoSite.security.AppUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



@Component
public class JwtService {
  private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

//  @Value("${bezkoder.app.jwtSecret}")
//  private String jwtSecret;  // todo normal

  private static final String jwtSecret = "59703373367639792F423F4528482B4D6251655468576D5A7134743777217A25432646294A404E635266556A586E3272357538782F413F4428472D4B61506453";


  private final int jwtExpirationMs = 1000*60*24;




//  public String generateJwtToken(Authentication authentication) {
//
//    AppUserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//    return Jwts.builder()
//        .setSubject((userPrincipal.getUsername()))
//        .setIssuedAt(new Date())
//        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//        .signWith(SignatureAlgorithm.HS512, jwtSecret)
//        .compact();
//  }



  public String generateToken(UserDetails user) {
    return generateToken(new HashMap<>(), user);
  }

//  public String generateToken(
//          Map<String, Object> extraClaims,
//          UserDetails userDetails
//  ) {
//    return buildToken(extraClaims, userDetails, jwtExpiration);
//  }


  public String generateToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails
  ) {
//    return buildToken(extraClaims, userDetails, jwtExpiration);

    return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject((userDetails.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(getSignInKey(), SignatureAlgorithm.HS512)
            .compact();
  }

//  public String generateRefreshToken(
//          UserDetails userDetails
//  ) {
//    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
//  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }


  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }


  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }


  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    return Keys.hmacShaKeyFor(keyBytes);
  }



  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }


//  private Date extractExpiration(String token) {
//    return extractClaim(token, Claims::getExpiration);
//  }




}


//@Service
//public class JwtService {
//
//  @Value("${application.security.jwt.secret-key}")
//  private String secretKey;
//  @Value("${application.security.jwt.expiration}")
//  private long jwtExpiration;
//  @Value("${application.security.jwt.refresh-token.expiration}")
//  private long refreshExpiration;
//
//  public String extractUsername(String token) {
//    return extractClaim(token, Claims::getSubject);
//  }
//
//  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//    final Claims claims = extractAllClaims(token);
//    return claimsResolver.apply(claims);
//  }
//
//  public String generateToken(UserDetails userDetails) {
//    return generateToken(new HashMap<>(), userDetails);
//  }
//
//  public String generateToken(
//          Map<String, Object> extraClaims,
//          UserDetails userDetails
//  ) {
//    return buildToken(extraClaims, userDetails, jwtExpiration);
//  }
//
//  public String generateRefreshToken(
//          UserDetails userDetails
//  ) {
//    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
//  }
//
//  private String buildToken(
//          Map<String, Object> extraClaims,
//          UserDetails userDetails,
//          long expiration
//  ) {
//    return Jwts
//            .builder()
//            .setClaims(extraClaims)
//            .setSubject(userDetails.getUsername())
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + expiration))
//            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//            .compact();
//  }
//
//  public boolean isTokenValid(String token, UserDetails userDetails) {
//    final String username = extractUsername(token);
//    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//  }
//
//  private boolean isTokenExpired(String token) {
//    return extractExpiration(token).before(new Date());
//  }
//
//  private Date extractExpiration(String token) {
//    return extractClaim(token, Claims::getExpiration);
//  }
//
//  private Claims extractAllClaims(String token) {
//    return Jwts
//            .parserBuilder()
//            .setSigningKey(getSignInKey())
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//  }
//
//  private Key getSignInKey() {
//    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//    return Keys.hmacShaKeyFor(keyBytes);
//  }
//}

