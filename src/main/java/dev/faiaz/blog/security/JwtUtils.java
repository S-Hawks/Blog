package dev.faiaz.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
 
    //2 implementation for generating token
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    //1 implementation for generating token
    public String generateToken(Map<String, Object> extraClaim, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //4 implementation
    public String extractUsername(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    //3 implementation
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    //1 implementation
    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    //2 implementation
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //1 implementation for validating token
    public boolean isValidateToken(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    //2 implementation for validating token
    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    //3 implementation for validating token
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

}
