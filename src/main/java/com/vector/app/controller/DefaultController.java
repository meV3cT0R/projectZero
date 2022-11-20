package com.vector.app.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;
import com.vector.app.service.UtilityService;

@Controller
public class DefaultController {
    private UserRepository userRepo;
    private UtilityService utilityService;
    
    

    public DefaultController(UserRepository userRepo, UtilityService utilityService) {
        this.userRepo = userRepo;
        this.utilityService = utilityService;
    }

    @GetMapping(path={"/","/login","/home"})
    public String getHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            Object principal = auth.getPrincipal();
            if(principal instanceof User) {
                model.addAttribute("user",(User) principal);
                return "userhome";
            }
        }
        return "home";
    }

    @PostMapping("/addfriend")
    public String processAddFriend(@RequestParam("friendUsername") String username,@AuthenticationPrincipal User user) throws Exception{
        if(!utilityService.authenticated())
            return "home";
        Optional<User> newFriend = userRepo.findByUsername(username);
        if(newFriend.isPresent()){
            user.addFriend(newFriend.get());
            userRepo.save(user);
            return "userdetail";
        }
        throw new Exception("User you are trying to add as a friend was not found");
    }

}
