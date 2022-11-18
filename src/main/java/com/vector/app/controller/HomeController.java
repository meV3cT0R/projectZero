package com.vector.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.User;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String getHomePage(@AuthenticationPrincipal User user,Model model) {
        model.addAttribute("user",user);
        return "userhome";
    }
}
