package com.brokagehouse.userservice.infrastructure.kafka.dto;

public record UserCredentials(String email, char[] password) {
}
