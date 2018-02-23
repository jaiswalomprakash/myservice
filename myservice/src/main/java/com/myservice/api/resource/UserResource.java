package com.myservice.api.resource;

import java.util.HashSet;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.myservice.api.entity.User;
import com.myservice.api.model.QueryUserResult;
import com.myservice.api.resource.mapper.UserAssembler;
import com.myservice.api.service.UserService;

/**
 * JAX-RS resource class for users.
 *
 * @author omprakash
 */
@Component
@Path("users")
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;
    /**
     * Get all users.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   // @PreAuthorize("hasAuthority('ADMIN')")
    public Response getUsers() {

        Iterable<User> iterable = userService.findAllUsers();
       /* List<QueryUserResult> queryDetailsList =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(this::toQueryResult)
                        .collect(Collectors.toList());

        return Response.ok(userAssembler.toResources(iterable)).build();*/
        
        

      
        List<QueryUserResult> resources = userAssembler.toResources(iterable);

        // wrap to add link
        Resources<QueryUserResult> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(UserResource.class)
                        .withSelfRel()
        );

        return Response.ok(wrapped).build();
        
       // return Response.ok(queryDetailsList).build();
    }

    /**
     * Get a user with the given identifier.
     *
     * @param userId
     * @return
     */
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Response getUser(@PathParam("userId") Long userId) {

        User user = userService.findUser(userId);
        if (user == null) {
            throw new NotFoundException();
        }

        QueryUserResult result = toQueryResult(user);
        return Response.ok(result).build();
    }

    /**
     * Get details from the current user.
     *
     * @return
     */
    @GET
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            QueryUserResult result = new QueryUserResult();
            result.setUsername(authentication.getName());
            result.setAuthorities(new HashSet<>());
            return Response.ok(result).build();
        }

        User user = userService.findByUsernameOrEmail(authentication.getName());
        QueryUserResult result = toQueryResult(user);
        return Response.ok(result).build();
    }

    /**
     * Map a {@link User} instance to a {@link QueryUserResult} instance.
     *
     * @param user
     * @return
     */
    private QueryUserResult toQueryResult(User user) {
        QueryUserResult result = new QueryUserResult();
        result.setUserID(user.getUserID());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(user.getEmail());
        result.setUsername(user.getUsername());
        result.setAuthorities(user.getAuthorities());
        result.setActive(user.isActive());
        return result;
    }
}
