package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.ErrorResponse;
import ir.aminahmadi24.dtos.UserRegisterRequest;
import ir.aminahmadi24.dtos.UserRegisterResponse;
import ir.aminahmadi24.entities.User;
import ir.aminahmadi24.exceptions.DuplicatedEmailException;
import ir.aminahmadi24.mappers.UserMapper;
import ir.aminahmadi24.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> register(
            @Valid @RequestBody UserRegisterRequest request,
            UriComponentsBuilder uriBuilder){
        User user = userService.register(request);
        URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userMapper.toUserRegisterResponse(user));
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    private ResponseEntity<ErrorResponse> handleDuplicatedEmail(DuplicatedEmailException e){
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
}
