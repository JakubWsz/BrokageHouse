package com.brokagehouse.userservice.infrastructure.repository;

import com.brokagehouse.userservice.infrastructure.entity.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findByEmailAddress(String emailAddress);
    Page<UserDao>findAll(Pageable pageable);

    boolean existsByEmailAddress(String name);

    boolean existsByPersonalIdNumber(String name);
}
