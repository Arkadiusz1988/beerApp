package com.beer.beer.service;

import com.beer.beer.models.User;



public interface UserService {

    User findByToken(String token);

    void generateNewToken(String token);

}