package com.myservice.api.model;

import java.io.Serializable;
import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myservice.api.security.domain.Authority;
/**
 * API model for returning user details.
 *
 * @author omprakash
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryUserResult extends ResourceSupport implements Serializable{

    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<Authority> authorities;
    private Boolean active;

   

    public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}