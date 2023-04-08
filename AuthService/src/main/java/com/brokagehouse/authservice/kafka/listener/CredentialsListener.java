package com.brokagehouse.authservice.kafka.listener;

import com.brokagehouse.authservice.auth.db.UserAuthJPARepository;
import com.brokagehouse.authservice.auth.encoder.PBKDF2Encoder;
import com.brokagehouse.authservice.auth.model.Role;
import com.brokagehouse.authservice.auth.model.UserAuth;
import com.brokagehouse.authservice.kafka.dto.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CredentialsListener {
    ObjectMapper objectMapper;
    UserAuthJPARepository userAuthJPARepository;
    PBKDF2Encoder pbkdf2Encoder;

    @KafkaListener(topics = "register-user", groupId = "group-1")
    public void listen(String value) {
        log.info("Mapping user credentials {} ", value);
        try {
            var userCredentials = objectMapper.readValue(value, UserCredentials.class);
            String hashPassword = pbkdf2Encoder.encode(Arrays.toString(userCredentials.getPassword()));
            userAuthJPARepository.save(new UserAuth(
                    userCredentials.email(), hashPassword, true,
                    new ArrayList<>(Collections.singleton(Role.ROLE_REGULAR))));
        } catch (Exception e) {
            log.error("Exception occurred during mapping user data: ", e);
        }
    }
}
