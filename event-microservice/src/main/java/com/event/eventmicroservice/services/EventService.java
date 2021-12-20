package com.event.eventmicroservice.services;

import com.event.eventmicroservice.models.Event;
import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.models.dtos.UserDTO;
import com.event.eventmicroservice.repositories.EventRepository;
import com.event.eventmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    public EventService(EventRepository eventRepository, ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Event save(Event event){
        return eventRepository.save(event);
    }

    public Iterable<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Iterable<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public UserDTO retrieveClient(String s) {

        return restTemplate.exchange(String.format("http://USER-FACTORY/api/auth/user/%s", s),
                HttpMethod.GET,
                null,
                UserDTO.class).getBody();
    }
}
