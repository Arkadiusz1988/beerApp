package com.beer.beer.service;

import com.beer.beer.dto.UserDto;
import com.beer.beer.exception.NoAuthorizationException;
import com.beer.beer.exception.UserAlreadyExistsException;
import com.beer.beer.exception.UserNotFoundException;
import com.beer.beer.models.User;

public interface AuthService {

    UserDto registerUser(User user) throws UserAlreadyExistsException;

    boolean loginUser(User user) throws UserNotFoundException;

    boolean valid(String tokenFromHeader) throws NoAuthorizationException;



    //void userLogout(User user);
}