package com.programmers.security;

import com.programmers.domain.User;
import com.programmers.exception.InvalidTokenException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    String secretKey;

    @Value("${app.jwt.token-validity-in-seconds}")
    int tokenValidityInSeconds;

    public String createToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusSeconds(tokenValidityInSeconds);

        Claims claims = Jwts.claims().setSubject(user.getNickName());
        claims.put("auth", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public void invalidateToken(String accessToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        long expTime = (long) claims.get("exp") * 1000; // convert to milliseconds
        Date expiryDate = new Date(expTime);

        if (expiryDate.before(new Date())) {
            throw new InvalidTokenException();
        }
        claims.setExpiration(new Date());
    }
}
