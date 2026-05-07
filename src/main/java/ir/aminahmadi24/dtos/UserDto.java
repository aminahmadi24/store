package ir.aminahmadi24.dtos;

import ir.aminahmadi24.enums.UserRole;
import lombok.AllArgsConstructor;


public record UserDto(Long id, String name, String email, UserRole role) {
}
