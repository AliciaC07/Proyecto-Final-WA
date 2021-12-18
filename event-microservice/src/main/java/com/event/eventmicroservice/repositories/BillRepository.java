package com.event.eventmicroservice.repositories;

import com.event.eventmicroservice.models.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
