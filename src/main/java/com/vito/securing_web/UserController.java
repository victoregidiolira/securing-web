package com.vito.securing_web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserEntity userEntity, BindingResult result){
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.register(userEntity);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Password")) {
                result.rejectValue("password", "error.password", e.getMessage());
            } else {
                result.rejectValue("username", "error.username", e.getMessage());
            }
            return "register";
        }
        return "redirect:/login";
    }
}