package com.event.eventmicroservice.controller;

import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/products")
    public Iterable<Product> products(){
        return eventService.findAllProducts();
    }
}
