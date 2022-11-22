package com.vector.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vector.app.model.Notification;
import com.vector.app.model.User;
import com.vector.app.repository.NotificationRepository;
import com.vector.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepo;
    private NotificationRepository notRepo;
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    public UserServiceImpl(UserRepository userRepo, NotificationRepository notRepo) {
        this.userRepo = userRepo;
        this.notRepo = notRepo;
    }

    @Override
    public List<Notification> getNotification(User user) {
        return null;
    }

    @Override
    public void addFriend(User user, User toAdd) {
        if(user.getFriends() != null) {
            user.getFriends().add(toAdd);
            log.info("list not null");
            userRepo.save(user);
            return;
        }
        log.info("List Null");
        List<User> newFriendList = new ArrayList<>();
        newFriendList.add(toAdd);
        user.setFriends(newFriendList);
        userRepo.save(user);
    }

    
    
}
