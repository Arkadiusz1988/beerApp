package com.beer.beer.controllers;

import com.beer.beer.repository.UserRepository;
import com.beer.beer.service.AuthService;
import com.beer.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(AuthService authService, UserRepository userRepository, UserService userService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model) {
        if (!userRepository.existsByLogin()) {
            return "redirect:/login";
        }
        model.addAttribute("user", authService.getUser());
        return "index";
    }



}
