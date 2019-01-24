package com.beer.beer.models;

import com.beer.beer.dto.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    private String password;

    @Email
    @NotEmpty
    private String email;

    private boolean active;

    private String token;

    public UserDto toUserDto() {
        UserDto user = new UserDto();

        user.setLogin(login);
        user.setEmail(email);

        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }
}