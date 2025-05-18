package io.github.sersul.feedcollector.controllers;

import io.github.sersul.feedcollector.dto.request.security.LoginRequestDTO;
import io.github.sersul.feedcollector.dto.request.security.RegistrationRequestDTO;
import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.dto.response.UserDto;
import io.github.sersul.feedcollector.service.UserContext;
import io.github.sersul.feedcollector.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserContext userContext;
    private final AuthenticationManager authenticationManager;

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

    @GetMapping("/me")
    public ResponseEntity<StandardResponse> me(HttpServletRequest httpRequest) {
        var user = userContext.getCurrentUser();
        return ResponseEntity.ok(new StandardResponse(true, null, user != null ? new UserDto(user.getId(), user.getUsername(), user.getEmail()) : null));
    }

    @PostMapping("/logout")
    public ResponseEntity<StandardResponse> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new StandardResponse(true, "Сессия завершена"));
    }
}
