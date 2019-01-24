package com.beer.beer.service.impl;

import com.beer.beer.models.User;
import com.beer.beer.repository.UserRepository;
import com.beer.beer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public void generateNewToken(String token) {

        User fromToken = userRepository.findByToken(token);
        fromToken.setToken(UUID.randomUUID().toString());
        userRepository.save(fromToken);
    }

}