package com.brokagehouse.userservice.api.dto;

import com.brokagehouse.userservice.domain.model.Address;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String firstname;
    String lastname;
    String emailAddress;
    String password;
    Long phoneNumber;
    String personalIdNumber;
    Address address;
}
