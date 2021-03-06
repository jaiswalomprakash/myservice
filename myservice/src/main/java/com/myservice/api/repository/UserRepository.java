package com.myservice.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myservice.api.entity.User;

/**
 * Repository for {@link User}s.
 *
 * @author omprakash
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findOne(Long id);

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?1")
    User findByUsernameOrEmail(String identifier);
}