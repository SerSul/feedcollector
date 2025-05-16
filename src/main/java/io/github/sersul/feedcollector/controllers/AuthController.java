package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.dto.request.security.LoginRequestDTO;
import io.github.sersul.feedcollector.dto.request.security.RegistrationRequestDTO;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.entity.security.User;
import io.github.sersul.feedcollector.repository.UserRepository;
import io.github.sersul.feedcollector.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@Valid @RequestBody RegistrationRequestDTO request) {
        var response = userService.registerUser(request);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> login(
            @Valid @RequestBody LoginRequestDTO request,
            HttpServletRequest httpRequest) {
        var response = userService.loginUser(request, httpRequest);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(401).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<StandardResponse> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new StandardResponse(true, "Сессия завершена"));
    }
}
