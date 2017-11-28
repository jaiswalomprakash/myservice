package com.myservice.api.security.api.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.myservice.api.security.api.AuthenticationTokenDetails;
import com.myservice.api.security.service.AuthenticationTokenService;

/**
 * Authentication provider for JWT token-based authentication.
 *
 * @author cassiomolin
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String authenticationToken = (String) authentication.getCredentials();
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationTokenDetails.getUsername());

        return new JwtAuthenticationToken(userDetails, authenticationTokenDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}