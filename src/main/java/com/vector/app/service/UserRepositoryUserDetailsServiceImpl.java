package com.vector.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;

public class UserRepositoryUserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepo;

    
    public UserRepositoryUserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username); 
        if(user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("Username:" +username+ "not found");
    }
    
}
