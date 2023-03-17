package com.brokagehouse.authorizationservice.mapper;

import com.brokagehouse.authorizationservice.dto.UserResponse;
import com.brokagehouse.authorizationservice.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DtoMapper {
    UserResponse toUserResponse(AuthUser user);
}
