package com.brokagehouse.authorizationservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String email;
    String password;
}
