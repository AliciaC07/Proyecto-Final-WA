package com.event.eventmicroservice.repositories;

import com.event.eventmicroservice.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
