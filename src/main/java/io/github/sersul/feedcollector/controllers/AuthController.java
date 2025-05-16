package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.dto.request.security.LoginRequestDTO;
import io.github.sersul.feedcollector.dto.request.security.RegistrationRequestDTO;
import io.github.sersul.feedcollector.entity.security.User;
import io.github.sersul.feedcollector.repository.UserRepository;
import io.github.sersul.feedcollector.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequestDTO request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequestDTO request,
            HttpSession session
    ) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный email или пароль");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный email или пароль");
        }

        session.setAttribute("userId", user.getId());

        return ResponseEntity.ok("Вы успешно вошли. ID сессии: " + session.getId());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Вы вышли из системы");
    }
}
