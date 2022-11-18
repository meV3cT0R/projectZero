package com.vector.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;

@Service
public class UserRepositoryUserDetailsServiceImpl implements UserDetailsService{
    UserRepository userRepo;

    @Autowired
    public UserRepositoryUserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username); 
        if(user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("Username:" +username+ "not found");
    }
    
}
