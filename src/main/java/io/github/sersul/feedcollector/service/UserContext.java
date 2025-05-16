package io.github.sersul.feedcollector.service;

import io.github.sersul.feedcollector.dto.response.StandardResponse;
import io.github.sersul.feedcollector.entity.security.User;
import io.github.sersul.feedcollector.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContext {


    private final UserRepository userRepository;

    public UserContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }

        var userName = authentication.getName();

        var userOpt = userRepository.findByUsername(userName);
        return userOpt.orElse(null);

    }
}
