package dev.faiaz.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    public String extractUsername(String jwt){
        return null;
    }
    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKe())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
    }
}
