package com.brokagehouse.userservice.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Embeddable
public class Address {
    String city;
    String postalCode;
    String streetAndNumber;
}
