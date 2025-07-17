package com.calorie.tracker.controller;

import com.calorie.tracker.entity.User;
import com.calorie.tracker.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        authService.register(request.getUsername(), request.getPassword());
        return "redirect:/login";
    }

    @Data
    public static class SignupRequest {
        private String username;
        private String password;
    }
}
