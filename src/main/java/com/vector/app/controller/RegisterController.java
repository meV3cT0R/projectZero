package com.vector.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.RegisterUser;
import com.vector.app.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {
    UserRepository userRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("registerUser", new RegisterUser());
        return "register";
    }

    @PostMapping
    public String processRegisterForm(@Valid RegisterUser registerUser,Errors error) {
        if(error.hasErrors())
            return "register";
        userRepo.save(registerUser.toUser(passwordEncoder));
        return "redirect:/home";
    }
}
