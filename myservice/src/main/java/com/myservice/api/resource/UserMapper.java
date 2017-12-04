package com.myservice.api.resource;

import org.mapstruct.Mapper;

import com.myservice.api.entity.User;
import com.myservice.api.model.QueryUserResult;


 
/**
 * @author raidentrance
 *
 */
 
@Mapper
public interface UserMapper {
	QueryUserResult userEntityToUser(User entity); 
    User userToUserEntity(QueryUserResult dto);
 
   
}