package com.brokagehouse.authorizationservice.repository;

import com.brokagehouse.authorizationservice.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthUser,Long> {

    Optional<AuthUser> findByEmail(String uuid);
}
