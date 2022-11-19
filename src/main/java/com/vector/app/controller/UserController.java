package com.vector.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.Post;
import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    Logger log = LoggerFactory.getLogger(UserController.class);
    
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{username}")
    public String getUserDetail(Model model,@PathVariable("username") String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth !=null){
            User user = auth.getPrincipal() instanceof User ? (User) auth.getPrincipal() : null;   
            model.addAttribute("user",user);
            return "userdetail";
        }
        return "home";
    }
    @GetMapping("{username}/posts")
    public String getUserPosts(Model model,@PathVariable("username") String username) throws Exception{
        log.info(username);
        Optional<User> user = userRepo.findByUsername(username);
        if(!user.isPresent()){
            return "home";
        }
        List<Post> posts = user.get().getPosts();

        model.addAttribute("posts", posts);
        return "userPosts";
    }
}
