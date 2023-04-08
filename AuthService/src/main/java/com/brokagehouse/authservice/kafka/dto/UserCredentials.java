package com.brokagehouse.authservice.kafka.dto;

public record UserCredentials(String email, char[] password) {
    public char[] getPassword() {
        return password;
    }
}
