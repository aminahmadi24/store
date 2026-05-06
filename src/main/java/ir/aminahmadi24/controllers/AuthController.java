package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.ErrorResponse;
import ir.aminahmadi24.dtos.UserLoginRequest;
import ir.aminahmadi24.dtos.JwtResponse;
import ir.aminahmadi24.exceptions.UserNotFoundException;
import ir.aminahmadi24.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody UserLoginRequest request,
            HttpServletResponse response
    ){
        String accessToken = authService.login(request, response);
        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<ErrorResponse> handleUserNotFound(BadCredentialsException ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage() + ". Email or password is wrong"));
    }
}
