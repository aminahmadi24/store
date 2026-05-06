package ir.aminahmadi24.configs;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@Getter
public class JwtConfig {
    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    @Value("${spring.jwt.accessTokenExpiration}")
    private int accessTokenExpiration;

    @Value("${spring.jwt.refreshTokenExpiration}")
    private int refreshTokenExpiration;

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
