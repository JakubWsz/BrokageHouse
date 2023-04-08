package com.brokagehouse.userservice.domain.service;

import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.infrastructure.kafka.dto.VerificationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    User update(User toUpdate);
    boolean existsByEmailAddress(String email);
    boolean existsByPersonalIdNumber(String idNumber);

    Page<User> findUsers(Pageable pageable);

    void verify(VerificationResult result);
}
