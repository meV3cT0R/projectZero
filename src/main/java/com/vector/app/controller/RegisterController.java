package com.vector.app.controller;

import javax.validation.Valid;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.RegisterUser;
import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {
    UserRepository userRepo;
    PasswordEncoder passwordEncoder;
    DefaultController defaultController;

    
    
    public RegisterController(UserRepository userRepo, PasswordEncoder passwordEncoder,
            DefaultController defaultController) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.defaultController = defaultController;
    }

    @GetMapping
    public String registerForm(Model model,@AuthenticationPrincipal User user){
        if(user != null)
            return defaultController.getHomePage(model);
            
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
