package com.hdfc.budgets.jwt;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Replace with your property for the JWT secret

    public String extractUsername(String jwtToken) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken);

        Claims claims = claimsJws.getBody();
        return claims.getSubject();
    }
    
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
