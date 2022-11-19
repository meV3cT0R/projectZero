package com.vector.app.controller;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.User;

import org.springframework.web.bind.annotation.GetMapping;





@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String getLoginForm(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
            model.addAttribute("user", (User) principal);
            return "userhome";
        }
        return "home";
    }
}
