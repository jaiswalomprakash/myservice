package com.myservicei.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myservice.api.AbstractApiTest;
import com.myservice.api.security.api.model.AuthenticationToken;
import com.myservice.api.security.api.model.UserCredentials;

/**
 * Tests for the authentication resource class.
 *
 * @author omprakash
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationResourceTest extends AbstractApiTest {

    @Test
    public void authenticateWithValidCredentials() {

        UserCredentials credentials = new UserCredentials();
        credentials.setUsername("admin");
        credentials.setPassword("password");

        Response response = client.target(baseUri).path("auth").request()
                .post(Entity.entity(credentials, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        AuthenticationToken authenticationToken = response.readEntity(AuthenticationToken.class);
        assertNotNull(authenticationToken);
        assertNotNull(authenticationToken.getToken());
    }

    @Test
    public void authenticateWithInvalidCredentials() {

        UserCredentials credentials = new UserCredentials();
        credentials.setUsername("invalid-user");
        credentials.setPassword("wrong-password");

        Response response = client.target(baseUri).path("auth").request()
                .post(Entity.entity(credentials, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }
}
