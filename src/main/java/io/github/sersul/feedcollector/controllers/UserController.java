package io.github.sersul.feedcollector.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @PostMapping("/save-username")
    public String saveUsername(@RequestParam String username,
                               HttpServletResponse response) {
        // Устанавливаем куки на 1 год
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(365 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/clear-username")
    public String clearUsername(HttpServletResponse response) {
        // Удаляем куки
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}
