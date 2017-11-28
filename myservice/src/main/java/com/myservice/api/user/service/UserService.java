package com.myservice.api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myservice.api.user.domain.User;
import com.myservice.api.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for {@link User}s.
 *
 * @author omprakash
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    public User findByUsernameOrEmail(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier);
    }
}
