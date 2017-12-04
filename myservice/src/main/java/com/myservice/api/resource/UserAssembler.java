package com.myservice.api.resource;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.myservice.api.config.JaxRsResourceAssemblerSupport;
import com.myservice.api.entity.User;
import com.myservice.api.model.QueryUserResult;


/**
 * @author raidentrance
 *
 */
@Component
public class UserAssembler extends JaxRsResourceAssemblerSupport<User, QueryUserResult> {
   
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);
 
    public UserAssembler() {
        super(UserResource.class, QueryUserResult.class);
    }
 
    @Override
    public QueryUserResult toResource(User entity) {
    	QueryUserResult resource = createResourceWithId(entity.getUserId(), entity);
    	QueryUserResult result = mapper.userEntityToUser(entity);
        //RoleDto role = assembler.toResource(entity.getRole());
        result.add(resource.getLinks());
        //result.setRole(role);
        return result;
    }
}
