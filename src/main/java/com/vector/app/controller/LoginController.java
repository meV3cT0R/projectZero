package com.vector.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String getLoginPage() {
        return "home";
    }

    @RequestMapping("/fail")
    public String loginFail(Model model) {
        model.addAttribute("error", true);
        return "home";
    }
    
}
