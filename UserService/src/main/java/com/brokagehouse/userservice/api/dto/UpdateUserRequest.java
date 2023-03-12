package com.brokagehouse.userservice.api.dto;

import com.brokagehouse.userservice.domain.model.Address;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    String password;
    Long phoneNumber;
    Address address;
}
