package com.brokagehouse.userservice.infrastructure.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    public CompletableFuture<Void> push(Object object, String topic) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SendResult<String, String> result = kafkaTemplate.send(topic, objectMapper.writeValueAsString(object)).get();
                log.info("Sent data to Validation Service");
                log.debug("Sent '{}' to Validation Service offset '{}'", result.getProducerRecord().value(),
                        result.getRecordMetadata().offset());
                return null;
            } catch (Exception e) {
                log.error("Exception occurred during sending data to Validation Service", e);
                throw new RuntimeException(e);
            }
        });
    }
}

