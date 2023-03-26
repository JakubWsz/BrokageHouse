package com.brokagehouse.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    long id;
    String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String token;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    char[] password;
}
