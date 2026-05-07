package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.UserDto;
import ir.aminahmadi24.dtos.UserRegisterResponse;
import ir.aminahmadi24.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRegisterResponse toUserRegisterResponse(User user){
        return new UserRegisterResponse(user.getId(), user.getName(), user.getEmail());
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
