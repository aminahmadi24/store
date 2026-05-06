package ir.aminahmadi24.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRegisterRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 24, message = "Password should be between 6 and 24 characters long")
    private String password;
}
