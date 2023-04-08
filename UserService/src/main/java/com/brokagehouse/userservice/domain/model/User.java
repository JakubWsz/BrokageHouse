package com.brokagehouse.userservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class User {
    String firstname;
    String lastname;
    String emailAddress;
    Long phoneNumber;
    String personalIdNumber;
    Address address;
    File personalIdPhoto;
    boolean verified;
    LocalDateTime modificationDate;

    public User(String firstname, String lastname, String emailAddress,
                Long phoneNumber, String personalIdNumber, Address address, File personalIdPhoto) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.personalIdNumber = personalIdNumber;
        this.address = address;
        this.personalIdPhoto = personalIdPhoto;
    }

    public User(String firstname, String lastname, String emailAddress, Long phoneNumber,
                String personalIdNumber, Address address, File personalIdPhoto, LocalDateTime modificationDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.personalIdNumber = personalIdNumber;
        this.address = address;
        this.personalIdPhoto = personalIdPhoto;
        this.modificationDate = modificationDate;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
