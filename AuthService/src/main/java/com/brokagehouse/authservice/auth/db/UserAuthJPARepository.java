package com.brokagehouse.authservice.auth.db;

import com.brokagehouse.authservice.auth.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthJPARepository extends JpaRepository<UserAuth,Integer> {
   Optional<UserAuth> findByUsername(String username);
}
