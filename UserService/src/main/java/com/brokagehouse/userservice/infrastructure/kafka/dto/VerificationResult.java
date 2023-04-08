package com.brokagehouse.userservice.infrastructure.kafka.dto;

public record VerificationResult(String email, boolean result) {
}
