package ir.aminahmadi24.filters;

import io.jsonwebtoken.Claims;
import ir.aminahmadi24.entities.User;
import ir.aminahmadi24.enums.UserRole;
import ir.aminahmadi24.exceptions.UserNotFoundException;
import ir.aminahmadi24.repositories.UserRepository;
import ir.aminahmadi24.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authHeader.replace("Bearer ", "");
        if(jwtService.isTokenExpired(accessToken)){
            filterChain.doFilter(request, response);
            return;
        }

        Claims payload = jwtService.getPayloadFromToken(accessToken);
        Long userId = Long.valueOf(payload.getSubject());
        UserRole role = UserRole.valueOf(payload.get("role", String.class));

        var authentication = new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        List.of(new SimpleGrantedAuthority("Role_"+ role)));
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
