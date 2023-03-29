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

    public void push(Object object, String topic) {
        try {
            CompletableFuture<SendResult<String, String>> futureSendResult
                    = kafkaTemplate.send(topic, objectMapper.writeValueAsString(object));

            //TODO: handle failure
            futureSendResult.whenComplete((res, ex) -> {
                log.info("Sent data to Validation Service");
                log.debug("Sent '{}' to Validation Service offset '{}'", res.getProducerRecord().value(),
                        res.getRecordMetadata().offset());
            });

//            futureSendResult.acceptEither(new ListenableFutureCallback<>() {
//                @Override
//                public void onFailure(Throwable ex) {
//                    log.error("Exception occurred during sending data to statistics", ex);
//                }
//
//                @Override
//                public void onSuccess(SendResult<String, String> result) {
//                    log.info("Sent data to Validation Service");
//                    log.debug("Sent '{}' to Validation Service offset '{}'", result.getProducerRecord().value(),
//                            result.getRecordMetadata().offset());
//                }
//            });
        } catch (Exception e) {
            log.error("Exception occurred during sending data to Validation Service", e);
        }
    }
}

