package com.brokagehouse.userservice.domain.service;

import com.brokagehouse.userservice.domain.exception.DomainExceptionCode;
import com.brokagehouse.userservice.domain.model.Address;
import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.infrastructure.kafka.dto.UserCredentials;
import com.brokagehouse.userservice.infrastructure.kafka.producer.KafkaProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    static String TO_VERIFICATION_USER_TOPIC = "to-verification-user";
    static String REGISTER_USER_TOPIC = "register-user";
    static String UPDATE_USER_TOPIC = "update-user";
    UserRepository userRepository;
    KafkaProducer kafkaProducer;
    ExecutorService executorService;

    public User createUser(
            String firstname, String lastname, String emailAddress, char[] password,
            Long phoneNumber, String personalIdNumber, Address address, File personalIdPhoto
    ) {
        var user = new User(firstname, lastname, emailAddress, phoneNumber, personalIdNumber,
                address, personalIdPhoto);

        validateUser(user);

        executorService.submit(() -> kafkaProducer.push(user, TO_VERIFICATION_USER_TOPIC));
        executorService.submit(() -> kafkaProducer.push(
                new UserCredentials(user.getEmailAddress(), password),
                REGISTER_USER_TOPIC));

        return userRepository.save(user);
    }

    public User updateUser(Address address, Long phoneNumber, char[] password, String email) {
        var fromDb = userRepository.findByEmail(email);
        Address addressToSave;
        Long phoneNumberToSave;

        if (address == null) {
            addressToSave = fromDb.getAddress();
        } else addressToSave = address;
        if (phoneNumber == null) {
            phoneNumberToSave = fromDb.getPhoneNumber();
        } else phoneNumberToSave = phoneNumber;
        if (password != null) {
            executorService.submit(() -> kafkaProducer.push(new UserCredentials(email, password), UPDATE_USER_TOPIC));
        }

        var toUpdate = new User(
                fromDb.getFirstname(),
                fromDb.getLastname(),
                fromDb.getEmailAddress(),
                phoneNumberToSave,
                fromDb.getPersonalIdNumber(),
                addressToSave,
                fromDb.getPersonalIdPhoto(),
                LocalDateTime.now()
        );
        return userRepository.update(toUpdate);
    }

    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findUsers(pageable);
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    private void validateUser(User user) {
        isEmailExists(user.getEmailAddress());
        isPersonalIdNumberExists(user.getPersonalIdNumber());
    }

    private void isEmailExists(String emailAddress) {
        if (userRepository.existsByEmailAddress(emailAddress)) {
            throw DomainExceptionCode.EMAIL_ALREADY_EXISTS.createValidationException();
        }
    }

    private void isPersonalIdNumberExists(String personalIdNumber) {
        if (userRepository.existsByPersonalIdNumber(personalIdNumber)) {
            throw DomainExceptionCode.PERSONAL_ID_NUMBER_ALREADY_EXISTS.createValidationException();
        }
    }
}
