package com.beer.beer.repository;


import com.beer.beer.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {



}
