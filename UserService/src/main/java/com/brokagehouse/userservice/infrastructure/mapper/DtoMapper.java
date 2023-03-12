package com.brokagehouse.userservice.infrastructure.mapper;

import com.brokagehouse.userservice.api.dto.UserResponse;
import com.brokagehouse.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DtoMapper {
    UserResponse toUserResponse(User user);
}
