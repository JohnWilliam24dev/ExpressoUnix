package com.johnwilliam.ExpressoUnix.Configs.Services;

import com.johnwilliam.ExpressoUnix.Configs.DTO.UserDTO;
import com.johnwilliam.ExpressoUnix.Configs.Models.User;
import com.johnwilliam.ExpressoUnix.Configs.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserDTO dto) {
        User user = mapToModel(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private User mapToModel(UserDTO dto) {
      return new User(dto.login(), dto.password(), dto.role());
    }

    public User findByLogin(String username) {
        return userRepository.findByLogin(username).get();
    }
}
