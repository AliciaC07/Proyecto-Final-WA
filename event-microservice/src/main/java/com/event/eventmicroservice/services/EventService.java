package com.event.eventmicroservice.services;

import com.event.eventmicroservice.models.Event;
import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.repositories.EventRepository;
import com.event.eventmicroservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ProductRepository productRepository;

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
}
