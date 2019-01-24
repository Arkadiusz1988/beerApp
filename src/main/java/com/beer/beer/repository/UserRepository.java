package com.beer.beer.repository;


import com.beer.beer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByToken(String token);

    boolean existsByLogin(String login);

}
