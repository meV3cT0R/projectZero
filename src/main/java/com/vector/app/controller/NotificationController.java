package com.vector.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.User;
import com.vector.app.repository.NotificationRepository;
import com.vector.app.repository.UserRepository;
import com.vector.app.service.UtilityService;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    private UserRepository userRepo;
    private UtilityService util;
    private NotificationRepository notiRepo;

    

    public NotificationController(UserRepository userRepo, UtilityService util, NotificationRepository notiRepo) {
        this.userRepo = userRepo;
        this.util = util;
        this.notiRepo = notiRepo;
    }



    @GetMapping
    public String getAllNotification(Model model,@AuthenticationPrincipal User user) {
        return "notification";
    }
}
