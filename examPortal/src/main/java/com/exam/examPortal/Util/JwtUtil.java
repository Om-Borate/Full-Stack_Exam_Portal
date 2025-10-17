package com.exam.examPortal.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final String Secret="my-super-secret-long-key-that-is-enough-123456@!";
    private final SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes());
    private final long ExpirationTime=1000*60*60;
   public  String loginUser(String email){
       return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ExpirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        Claims body = getBody(token); // this returns the email you set in loginUser()
        return body.getSubject();
    }

    private Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
       String username=extractUsername(token);
       return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getBody(token).getExpiration();
        System.out.println("Token expires at: " + expiration);
        return expiration.before(new Date());

    }
}
