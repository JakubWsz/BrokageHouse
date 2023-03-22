package com.brokagehouse.userservice.infrastructure.mapper;

import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.infrastructure.entity.UserDao;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T18:25:47+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class DaoMapperImpl implements DaoMapper {

    @Override
    public UserDao toUserDao(User user) {
        if ( user == null ) {
            return null;
        }

        UserDao userDao = new UserDao();

        userDao.setFirstname( user.getFirstname() );
        userDao.setLastname( user.getLastname() );
        userDao.setEmailAddress( user.getEmailAddress() );
        userDao.setPassword( user.getPassword() );
        userDao.setPhoneNumber( user.getPhoneNumber() );
        userDao.setPersonalIdNumber( user.getPersonalIdNumber() );
        userDao.setAddress( user.getAddress() );
        userDao.setPersonalIdPhoto( user.getPersonalIdPhoto() );
        userDao.setModificationDate( user.getModificationDate() );

        return userDao;
    }

    @Override
    public User toUser(UserDao userDao) {
        if ( userDao == null ) {
            return null;
        }

        User user = new User();

        user.setFirstname( userDao.getFirstname() );
        user.setLastname( userDao.getLastname() );
        user.setEmailAddress( userDao.getEmailAddress() );
        user.setPassword( userDao.getPassword() );
        user.setPhoneNumber( userDao.getPhoneNumber() );
        user.setPersonalIdNumber( userDao.getPersonalIdNumber() );
        user.setAddress( userDao.getAddress() );
        user.setPersonalIdPhoto( userDao.getPersonalIdPhoto() );
        user.setModificationDate( userDao.getModificationDate() );
        if ( userDao.getVerified() != null ) {
            user.setVerified( userDao.getVerified() );
        }

        return user;
    }
}
