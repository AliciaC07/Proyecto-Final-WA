package com.event.eventmicroservice.repositories;

import com.event.eventmicroservice.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
