package com.beer.beer.service.impl;

import com.beer.beer.dto.UserDto;
import com.beer.beer.exception.NoAuthorizationException;
import com.beer.beer.exception.UserAlreadyExistsException;
import com.beer.beer.exception.UserNotFoundException;
import com.beer.beer.models.User;
import com.beer.beer.repository.UserRepository;
import com.beer.beer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean valid(String tokenFromHeader) throws NoAuthorizationException {

        if (userRepository.existsByLogin(userRepository.findByToken(tokenFromHeader).getLogin())) {
            return true;

        } else {
            throw new NoAuthorizationException("You dont have authorization, try to login");
        }
    }

    @Override
    public boolean loginUser(User toLogin) throws UserNotFoundException {

        boolean result = false;
        User fromDb;

        try {
            fromDb = userRepository.findByLogin(toLogin.getLogin());

            boolean isPassCorrect =
                    bCryptPasswordEncoder.matches(toLogin.getPassword(), fromDb.getPassword());
            boolean isLoginCorrect = fromDb.getLogin().equals(toLogin.getLogin());

            if (isPassCorrect && isLoginCorrect) {
                fromDb.setToken(UUID.randomUUID().toString());
                userRepository.save(fromDb);

                result = true;
            }

        } catch (Exception exception) {
            throw new UserNotFoundException("Incorrect data or user does not exist");
        }
        return result;
    }

    @Override
    public UserDto registerUser(User toRegister) throws UserAlreadyExistsException {

        if (!userRepository.existsByLogin(toRegister.getLogin())) {

            toRegister.setPassword(bCryptPasswordEncoder.encode(toRegister.getPassword()));
            toRegister.setActive(true);
            toRegister.setToken(UUID.randomUUID().toString());

            userRepository.save(toRegister);

        } else {
            throw new UserAlreadyExistsException("User with that login already exists");
        }
        return toRegister.toUserDto();
    }

//    @Override
//    public void userLogout(User userToLogout){
//      this.userToLogout=null;
//    }
}