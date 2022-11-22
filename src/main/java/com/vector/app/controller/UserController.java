package com.vector.app.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.Post;
import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;
import com.vector.app.service.UtilityService;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    private UtilityService utilityService;

    private Logger log = LoggerFactory.getLogger(UserController.class);
    

    public UserController(UserRepository userRepo, UtilityService utilityService) {
        this.userRepo = userRepo;
        this.utilityService = utilityService;
    }
    @GetMapping("{username}")
    public String getUserDetail(HttpServletRequest request,Model model,@PathVariable("username") String username,@AuthenticationPrincipal User authenticatedUser) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth !=null){
            Optional<User> user = userRepo.findByUsername(username);   
            if(!user.isPresent()){
                request.getSession().invalidate();
                return "home";
            }
            boolean canSendFriendRequest=false;
            
            if(!user.get().getUsername().equals(authenticatedUser.getUsername())) {
                canSendFriendRequest = user.get().getFriends().contains(authenticatedUser);

                canSendFriendRequest=true;
                    
            } 
            model.addAttribute("canSendFriendRequest",canSendFriendRequest);
            model.addAttribute("user",user.get());
            return "userdetail";
        }
        return "home";
    }
    @GetMapping("{username}/posts")
    public String getUserPosts(Model model,@PathVariable("username") String username,@AuthenticationPrincipal User activeUser) throws Exception{
        log.info(username);
        Optional<User> user = userRepo.findByUsername(username);
        if(!user.isPresent()){
            model.addAttribute("user", activeUser);
            return "userhome";
        }
        List<Post> posts = user.get().getPosts();
        posts.sort((x,y)->x.getCreatedAt().compareTo(y.getCreatedAt()));
        model.addAttribute("posts", posts);
        return "userPosts";
    }

    @GetMapping("/user/{username}/friends")
    public String getFriendList(Model model,@PathVariable("username") String username) throws Exception{
        if(!utilityService.authenticated())
            return "home";
        
        Optional<User> user = userRepo.findByUsername(username);
        if(!user.isPresent())
            throw new Exception("Authenticated User Cannot be found");
        
        return utilityService.loadToPath(model, "friendList", user.get().getFriends(), "userFriendList");
    }
}
