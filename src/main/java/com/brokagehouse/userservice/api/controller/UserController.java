package com.brokagehouse.userservice.api.controller;

import com.brokagehouse.userservice.api.dto.UpdateUserRequest;
import com.brokagehouse.userservice.api.dto.UserRequest;
import com.brokagehouse.userservice.api.dto.UserResponse;
import com.brokagehouse.userservice.domain.model.User;
import com.brokagehouse.userservice.domain.service.UserService;
import com.brokagehouse.userservice.infrastructure.mapper.DtoMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    UserService userService;
    DtoMapper dtoMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userService.createUser(
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getEmailAddress(),
                userRequest.getPassword(),
                userRequest.getPhoneNumber(),
                userRequest.getPersonalIdNumber(),
                userRequest.getAddress(),
                userRequest.getPersonalIdPhoto()
        );
        log.info("Created user with email address '{}'", user.getEmailAddress());
        return ResponseEntity.ok(dtoMapper.toUserResponse(user));
    }

    @PatchMapping("/{userEmail}")
    public ResponseEntity<UserResponse> updateUser(
            @RequestBody UpdateUserRequest userRequest, @PathVariable String userEmail) {
        User user = userService.updateUser(
                userRequest.getAddress(),
                userRequest.getPhoneNumber(),
                userRequest.getPassword(),
                userEmail
        );
        log.info("Updated user with email address '{}'", user.getEmailAddress());
        return ResponseEntity.ok(dtoMapper.toUserResponse(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findUsers(Pageable pageable) {
        log.info("Retrieving users");
        return ResponseEntity.ok(userService.findUsers(pageable)
                .map(dtoMapper::toUserResponse));
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<UserResponse> findUserByEmail(@PathVariable String userEmail) {
        log.info("Retrieving user with email: '{}'", userEmail);
        return ResponseEntity.ok(dtoMapper.toUserResponse(userService.findByEmail(userEmail)));
    }
}
