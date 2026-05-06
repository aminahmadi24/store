package ir.aminahmadi24.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserLoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
