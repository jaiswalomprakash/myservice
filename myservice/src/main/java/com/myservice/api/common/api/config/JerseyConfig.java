package com.myservice.api.common.api.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.myservice.api.greeting.api.resource.GreetingResource;
import com.myservice.api.security.api.exceptionmapper.AccessDeniedExceptionMapper;
import com.myservice.api.security.api.exceptionmapper.AuthenticationExceptionMapper;
import com.myservice.api.security.api.exceptionmapper.AuthenticationTokenRefreshmentExceptionMapper;
import com.myservice.api.security.api.resource.AuthenticationResource;
import com.myservice.api.user.api.resource.UserResource;

/**
 * Jersey configuration class.
 *
 * @author omprakash
 */
@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(AuthenticationResource.class);
        register(GreetingResource.class);
        register(UserResource.class);

        register(AccessDeniedExceptionMapper.class);
        register(AuthenticationExceptionMapper.class);
        register(AuthenticationTokenRefreshmentExceptionMapper.class);

    	register(MultiPartFeature.class);
    }
}