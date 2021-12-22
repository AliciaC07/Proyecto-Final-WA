package com.event.eventmicroservice.repositories;

import com.event.eventmicroservice.models.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Integer> {


    Optional<Bill> findByIdAndStatusFalse(Integer integer);
}
