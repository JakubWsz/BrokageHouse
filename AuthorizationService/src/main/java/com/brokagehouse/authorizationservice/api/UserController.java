package com.brokagehouse.authorizationservice.api;

import com.brokagehouse.authorizationservice.dto.UserResponse;
import com.brokagehouse.authorizationservice.entity.AuthUser;
import com.brokagehouse.authorizationservice.entity.Role;
import com.brokagehouse.authorizationservice.exception.DbExceptionCode;
import com.brokagehouse.authorizationservice.mapper.DtoMapper;
import com.brokagehouse.authorizationservice.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class UserController {
    UserService userService;
    DtoMapper mapper;


//    public AuthUser saveUser(AuthUser user) {
//
//    }
//
//    public Role saveRole(Role role) {
//
//    }

    public void addRoleToUser(String userEmail, String roleName) {

    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(String userEmail) {
        return ResponseEntity.ok(mapper.toUserResponse(userService.getUser(userEmail)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable)
                .map(mapper::toUserResponse));
    }
}
