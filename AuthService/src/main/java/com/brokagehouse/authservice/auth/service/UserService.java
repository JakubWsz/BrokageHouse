package com.brokagehouse.authservice.auth.service;

import com.brokagehouse.authservice.auth.db.UserAuthJPARepository;
import com.brokagehouse.authservice.auth.encoder.PBKDF2Encoder;
import com.brokagehouse.authservice.auth.model.Role;
import com.brokagehouse.authservice.auth.model.UserAuth;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {
    private final PBKDF2Encoder pbkdf2Encoder;
    private final UserAuthJPARepository userAuthJPARepository;

    public UserService(PBKDF2Encoder pbkdf2Encoder, UserAuthJPARepository userAuthJPARepository) {
        this.pbkdf2Encoder = pbkdf2Encoder;
        this.userAuthJPARepository = userAuthJPARepository;
    }

    public Mono<UserAuth> findByUsername(String username) {

        return Mono.justOrEmpty(userAuthJPARepository.findByUsername(username));
    }

    public UserAuth register(String username, String rawPassword) {
        String hashPassword = pbkdf2Encoder.encode(rawPassword);
        return userAuthJPARepository.save(new UserAuth(username, hashPassword,
                true, List.of(Role.ROLE_REGULAR)));
    }
}