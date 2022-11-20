package com.vector.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data

public class Post {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) long id;
    
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="userId",referencedColumnName="id")
    private User user;

    private String text;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date createdAt;

    public String dateToString() {
        return String.format("%d-%d-%d%n",createdAt.getMonth(),createdAt.getYear());
    }
}
