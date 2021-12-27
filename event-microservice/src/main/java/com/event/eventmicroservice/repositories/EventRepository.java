package com.event.eventmicroservice.repositories;

import com.event.eventmicroservice.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {

    Optional<Event> findEventByName(String name);
}
