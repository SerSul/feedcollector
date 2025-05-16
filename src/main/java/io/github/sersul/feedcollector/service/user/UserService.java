package io.github.sersul.feedcollector.service.user;

import io.github.sersul.feedcollector.dto.request.security.RegistrationRequestDTO;
import io.github.sersul.feedcollector.entity.security.User;
import io.github.sersul.feedcollector.repository.RolesRepository;
import io.github.sersul.feedcollector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    public void registerUser(RegistrationRequestDTO requestDTO) {
        String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(requestDTO.getEmail());
        var role = rolesRepository.findByName("ROLE_USER");
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
