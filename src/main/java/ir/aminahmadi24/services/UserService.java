package ir.aminahmadi24.services;

import ir.aminahmadi24.dtos.UserRegisterRequest;
import ir.aminahmadi24.entities.User;
import ir.aminahmadi24.exceptions.DuplicatedEmailException;
import ir.aminahmadi24.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicatedEmailException();

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword);
        return userRepository.save(user);


    }
}
