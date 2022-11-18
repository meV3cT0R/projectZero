package com.vector.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authenticate")
public class AuthenticationController {
    @RequestMapping("/fail")
    public String loginfailure(Model model) {
        model.addAttribute("error", true);
        return "home";
    }
}
