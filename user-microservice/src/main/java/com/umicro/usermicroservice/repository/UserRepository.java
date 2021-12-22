package com.umicro.usermicroservice.repository;

import com.umicro.usermicroservice.models.Role;
import com.umicro.usermicroservice.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsernameAndActiveTrue(String name);

    Iterable<User> findAllByActiveTrue();

    Optional<User> findUserByIdAndActiveTrue(Integer integer);

    Optional<User> findUserByEmailAndActiveTrue(String email);

    Iterable<User> findUserByRoleAndActiveTrue(Role role);
}
