package com.foodapp.restaurantservice.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET =
            "foodapp-secret-key-foodapp-secret-key";

    public String extractUsername(String token) {

        Claims claims =
                Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }
}