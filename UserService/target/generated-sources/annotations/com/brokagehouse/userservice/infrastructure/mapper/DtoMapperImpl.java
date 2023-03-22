package com.brokagehouse.userservice.infrastructure.mapper;

import com.brokagehouse.userservice.api.dto.UserResponse;
import com.brokagehouse.userservice.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T18:25:47+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class DtoMapperImpl implements DtoMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setFirstname( user.getFirstname() );
        userResponse.setLastname( user.getLastname() );
        userResponse.setEmailAddress( user.getEmailAddress() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setPhoneNumber( user.getPhoneNumber() );
        userResponse.setPersonalIdNumber( user.getPersonalIdNumber() );
        userResponse.setAddress( user.getAddress() );

        return userResponse;
    }
}
