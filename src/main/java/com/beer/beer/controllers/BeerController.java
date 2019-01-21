package com.beer.beer.controllers;


import com.beer.beer.models.Beer;
import com.beer.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @GetMapping("/beer")
    public List<Beer> beersList() {


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List<Beer>> response = restTemplate.exchange(
                "https://api.punkapi.com/v2/beers",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Beer>>(){});
        List<Beer> beers = response.getBody();

//        private static BoredActivity getBoredActivity() {
//            final String uri = "http://www.boredapi.com/api/activity/";
//
//            RestTemplate restTemplate = new RestTemplate();
//            String json = restTemplate.getForObject(uri, String.class);
//
//            return new Gson().fromJson(json, BoredActivity.class);
//        }

        return beers;
    }



}
