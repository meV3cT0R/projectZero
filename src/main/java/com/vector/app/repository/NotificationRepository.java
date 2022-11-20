package com.vector.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vector.app.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {
    
}
