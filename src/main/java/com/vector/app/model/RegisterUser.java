package com.vector.app.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegisterUser {
    @NotBlank
    @Size(min=3,max=20,message="2 < first name length < 21")
    public String firstName;

    @NotBlank
    @Size(min=3,max=20,message="2 < Last Name length < 21")
    public String lastName;

    @NotBlank
    @Size(min=5,max=20,message="5 < User Name length < 21")
    public String userName;

    @NotBlank
    @Size(min=8,max=32,message="7 < password length < 32")
    public String password;

    @NotBlank
    public String gender;

    @Email
    @NotBlank
    public String email;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    @NotNull
    public Date dateOfBirth;

    public User toUser(PasswordEncoder encoder) {
        Date joinedDate = new Date();
        return new User(firstName, lastName, userName, email, encoder.encode(password), dateOfBirth, joinedDate, gender);
    }
}
