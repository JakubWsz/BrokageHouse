package com.brokagehouse.userservice.infrastructure.entity;

import com.brokagehouse.userservice.domain.model.Address;
import com.brokagehouse.userservice.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;
import java.io.File;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstname;
    String lastname;
    @NaturalId
    @Column(unique = true)
    String emailAddress;
    String password;
    Long phoneNumber;
    @NaturalId
    @Column(unique = true)
    String personalIdNumber;
    @Embedded
    Address address;
    File personalIdPhoto;
    Boolean verified;

    boolean deleted;
    LocalDateTime modificationDate;

    public UserDao(Long id, User user) {
        this.id = id;
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.emailAddress = user.getEmailAddress();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.personalIdNumber = user.getPersonalIdNumber();
        this.address = user.getAddress();
        this.personalIdPhoto = user.getPersonalIdPhoto();
        this.verified = user.isVerified();
        this.modificationDate = user.getModificationDate();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPersonalIdPhoto(File personalIdPhoto) {
        this.personalIdPhoto = personalIdPhoto;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
