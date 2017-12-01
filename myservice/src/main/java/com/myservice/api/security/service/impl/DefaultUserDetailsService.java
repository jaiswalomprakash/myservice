package com.myservice.api.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myservice.api.entity.User;
import com.myservice.api.security.api.AuthenticatedUserDetails;
import com.myservice.api.security.domain.Authority;
import com.myservice.api.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default implementation for the {@link UserDetailsService}.
 *
 * @author omprakash
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        return new AuthenticatedUserDetails.Builder()
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withAuthorities(mapToGrantedAuthorities(user.getAuthorities()))
                .withActive(user.isActive())
                .build();
    }

    private Set<GrantedAuthority> mapToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toSet());
    }
}