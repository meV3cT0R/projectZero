package com.vector.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("{username}")
    public String getUserDetail(Model model,@PathVariable("username") String username) {
        model.addAttribute("name",username);
        return "userdetail";
    }
}
