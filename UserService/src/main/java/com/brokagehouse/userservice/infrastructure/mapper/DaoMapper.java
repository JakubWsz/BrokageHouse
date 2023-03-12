package com.brokagehouse.userservice.infrastructure.mapper;

import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.infrastructure.entity.UserDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DaoMapper {
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "emailAddress", source = "emailAddress")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "personalIdNumber", source = "personalIdNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "personalIdPhoto", source = "personalIdPhoto")
    @Mapping(target = "modificationDate", source = "modificationDate")
    @Mapping(target = "verified", ignore = true)
    UserDao toUserDao(User user);

    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "emailAddress", source = "emailAddress")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "personalIdNumber", source = "personalIdNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "personalIdPhoto", source = "personalIdPhoto")
    @Mapping(target = "modificationDate", source = "modificationDate")
    User toUser(UserDao userDao);
}
