package ir.aminahmadi24.services;

import ir.aminahmadi24.dtos.UserRegisterRequest;
import ir.aminahmadi24.entities.User;
import ir.aminahmadi24.exceptions.DuplicatedEmailException;
import ir.aminahmadi24.exceptions.UserNotFoundException;
import ir.aminahmadi24.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

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
