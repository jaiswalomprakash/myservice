package com.myservice.api.security.domain;

import com.myservice.api.entity.User;

/**
 * Authorities that can be granted to a {@link User}.
 *
 * @author omprakash
 */
public enum Authority {
    SUPER_USER,ADMIN,SITE_OWNER,USER
}
