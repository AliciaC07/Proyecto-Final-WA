package com.event.eventmicroservice.controller;

import com.event.eventmicroservice.models.Event;
import com.event.eventmicroservice.models.Product;
import com.event.eventmicroservice.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/products")
    public Iterable<Product> products(){
        return eventService.findAllProducts();
    }

    @GetMapping("/events")
    public Iterable<Event> eventsFindAll(){
        return eventService.getAllEvents();
    }

}
