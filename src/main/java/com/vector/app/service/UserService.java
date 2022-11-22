package com.vector.app.service;

import java.util.List;

import com.vector.app.model.Notification;
import com.vector.app.model.User;

public interface UserService {
    List<Notification> getNotification(User user);
    void addFriend(User user,User toAdd);
}
