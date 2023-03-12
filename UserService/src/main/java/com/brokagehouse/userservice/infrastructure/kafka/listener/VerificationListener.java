package com.brokagehouse.userservice.infrastructure.kafka.listener;

import com.brokagehouse.userservice.domain.service.UserRepository;
import com.brokagehouse.userservice.infrastructure.VerificationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class VerificationListener {
    ObjectMapper objectMapper;
    UserRepository userRepository;

    @KafkaListener(topics = "verify-user", groupId = "group-1")
    public void listen(String value) {
        log.info("mapping car {} ", value);
        try {
            userRepository.verify(objectMapper.readValue(value, VerificationResult.class));
        } catch (Exception e) {
            log.error("Exception occurred during mapping car data: ", e);
        }
    }
}