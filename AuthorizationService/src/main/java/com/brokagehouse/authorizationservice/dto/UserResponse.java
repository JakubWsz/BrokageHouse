package com.brokagehouse.authorizationservice.dto;

import com.brokagehouse.authorizationservice.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String email;
    String password;
    Set<Role> roles;
}
