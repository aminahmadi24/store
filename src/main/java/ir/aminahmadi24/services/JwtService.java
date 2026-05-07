package ir.aminahmadi24.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import ir.aminahmadi24.configs.JwtConfig;
import ir.aminahmadi24.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public String generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user){
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private String generateToken(User user, int tokenExpiration){
        return Jwts
                .builder()
                .subject(String.valueOf(user.getId()))
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (tokenExpiration * 1000)))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public Claims getPayloadFromToken(String token){
        return Jwts
                .parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token){
        try{
            Claims payload = getPayloadFromToken(token);
            return payload.getExpiration().before(new Date());
        }catch (JwtException ex){
            return true;
        }
    }


}
