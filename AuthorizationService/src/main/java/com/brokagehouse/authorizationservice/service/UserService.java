package com.brokagehouse.authorizationservice.service;

import com.brokagehouse.authorizationservice.entity.AuthUser;
import com.brokagehouse.authorizationservice.entity.Role;
import com.brokagehouse.authorizationservice.exception.DbException;
import com.brokagehouse.authorizationservice.exception.DbExceptionCode;
import com.brokagehouse.authorizationservice.repository.RoleRepository;
import com.brokagehouse.authorizationservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public AuthUser saveUser(AuthUser user) {
        log.info("Saving new user '{}'", user.getEmail());
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role '{}'", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String userEmail, String roleName) {
        log.info("Adding new role '{}' to user '{}'", roleName, userEmail);
        AuthUser user = getUserOrThrowException(userEmail);
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(DbExceptionCode.NO_ROLE::createDbException);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public AuthUser getUser(String userEmail) {
        log.info("Retrieving user '{}'", userEmail);
        return getUserOrThrowException(userEmail);
    }

    public Page<AuthUser> getAllUsers(Pageable pageable) {
        log.info("Retrieving users");
        return userRepository.findAll(pageable);
    }

    private AuthUser getUserOrThrowException(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(DbExceptionCode.NO_USER::createDbException);
    }
}
