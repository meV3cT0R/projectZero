package com.vector.app.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor()
@Table(name="users")
public class User implements UserDetails{
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final String password;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private final Date dateOfBirth;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private final Date joinedDate;

    private final String gender;

    @OneToMany(mappedBy="user")
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<User> friends;

    @ManyToMany()
    private List<User> friendOf;

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    } 

    @Override
    public boolean equals(Object o) {
        if(o instanceof User)
            return ((User) o).getUsername().equals(this.getUsername());
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    
}
