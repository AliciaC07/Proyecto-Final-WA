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
import java.util.ArrayList;
import java.util.List;

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

    public void insertEvents(){
        Product product = new Product();
        product.setName("Camera");
        Product product1 = new Product();
        product1.setName("Tripo");
        Product product2= new Product();
        product2.setName("Lights");
        Product product3 = new Product();
        product3.setName("Video Camera");
        Product product4 = new Product();
        product4.setName("Decoration");
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        productRepository.saveAll(products);
        Event event = new Event();
        event.setName("Pre-Wedding");
        event.setPrice(1000.00f);
        event.getProducts().add(product);
        event.getProducts().add(product3);
        Event event1 = new Event();
        event.setName("Wedding");
        event.setPrice(5000.00f);
        event.getProducts().add(product);
        event.getProducts().add(product3);
        event.getProducts().add(product2);
        event.getProducts().add(product4);
        Event event2 = new Event();
        event.setName("Birthday");
        event.setPrice(3000.00f);
        event.getProducts().add(product);
        event.getProducts().add(product2);
        event.getProducts().add(product4);
        Event event3 = new Event();
        event.setName("Event Video");
        event.setPrice(4000.00f);
        event.getProducts().add(product3);
        event.getProducts().add(product1);
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event1);
        events.add(event2);
        events.add(event3);
        eventRepository.saveAll(events);


    }

    public UserDTO retrieveClient(String s) {

        return restTemplate.exchange(String.format("http://USER-MICROSERVICE/user/auth/user/%s", s),
                HttpMethod.GET,
                null,
                UserDTO.class).getBody();
    }
}
