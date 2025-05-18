package io.github.sersul.feedcollector.service.user;

import io.github.sersul.feedcollector.dto.request.security.LoginRequestDTO;
import io.github.sersul.feedcollector.dto.request.security.RegistrationRequestDTO;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.entity.security.User;
import io.github.sersul.feedcollector.repository.RolesRepository;
import io.github.sersul.feedcollector.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;

    public StandardResponse registerUser(RegistrationRequestDTO requestDTO) {
        if (userRepository.existsByUsername(requestDTO.getUserName())) {
            return new StandardResponse(false,"Имя пользователя уже занято");
        }

        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            return new StandardResponse(false,"Email уже используется");
        }

        String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());

        User user = new User();
        user.setUsername(requestDTO.getUserName());
        user.setPassword(hashedPassword);
        user.setEmail(requestDTO.getEmail());

        var role = rolesRepository.findByName("ROLE_USER");

        user.getRoles().add(role);
        userRepository.save(user);
        return new StandardResponse(true, "Registered Successfully");
    }

    public StandardResponse loginUser(LoginRequestDTO request, HttpServletRequest httpRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(auth);

            // Привязать контекст к сессии
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return new StandardResponse(true, "Успешный вход");
        } catch (AuthenticationException e) {
            return new StandardResponse(false, "Неверный email или пароль");
        }
    }
}
