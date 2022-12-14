package com.vector.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Notification {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
    private String text;
    private Date createdAt;
    private boolean done;
    private Type type;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="userId",referencedColumnName = "id")
    private User user;
    enum Type{

    }
}
