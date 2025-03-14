package com.projectjava.demo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET = "qjpNAaKBj94nYhyvPejXuoWRF0FUGay+/XP5qy+T4eo="; // Your provided key
    private final SecretKey SECRET_KEY;

    public JwtUtil() {
        // Decode the Base64-encoded key and use it for HMAC SHA256
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000)) // 10-hour expiry
                .signWith(SECRET_KEY) // No need to specify algorithm separately
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, String userEmail) {
        return (extractEmail(token).equals(userEmail) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
