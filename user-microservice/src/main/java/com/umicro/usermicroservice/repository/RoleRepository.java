package com.umicro.usermicroservice.repository;

import com.umicro.usermicroservice.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByNameAndActiveTrue(String name);

    Iterable<Role> findAllByActiveTrue();

}
