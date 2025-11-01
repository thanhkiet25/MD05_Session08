package com.ra.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class JWTProvider {
    String secret ="200117";
    long expirationTime = 60*60*1000;

    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()!= null;
    }
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
