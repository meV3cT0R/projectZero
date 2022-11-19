package com.vector.app.controller;

import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vector.app.model.Post;
import com.vector.app.model.User;
import com.vector.app.repository.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {
    private PostRepository postRepo;
    
    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public String getPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping
    public String processPost(Model model,@AuthenticationPrincipal User user,Post post) {
        post.setUser(user);
        post.setCreatedAt(new Date());
        postRepo.save(post);
        model.addAttribute("sucess", "Wanna say something more?");
        return "post";
    }
}
