package com.beer.beer.dto;

import com.beer.beer.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {


    private String login;

    private String email;

    private String token;


    public User toUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setToken(token);
        return user;
    }

    public User toSave() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        return user;
    }




}

