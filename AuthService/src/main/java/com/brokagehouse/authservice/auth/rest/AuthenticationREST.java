package com.brokagehouse.authservice.auth.rest;

import com.brokagehouse.authservice.auth.encoder.PBKDF2Encoder;
import com.brokagehouse.authservice.auth.model.RequestAuth;
import com.brokagehouse.authservice.auth.model.ResponseAuth;
import com.brokagehouse.authservice.auth.model.UserAuth;
import com.brokagehouse.authservice.auth.service.UserService;
import com.brokagehouse.authservice.auth.utils.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthenticationREST {
    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder pbkdf2Encoder;
    private final UserService userService;

    public AuthenticationREST(JWTUtil jwtUtil, PBKDF2Encoder pbkdf2Encoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.pbkdf2Encoder = pbkdf2Encoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseAuth>> login(@RequestBody RequestAuth requestAuth, ServerHttpResponse response) {
        return userService.findByUsername(requestAuth.getUsername())
                .filter(userAuth -> pbkdf2Encoder.encode(requestAuth.getPassword()).equals(userAuth.getPassword()))
                .map(userAuth -> {
                    String token = jwtUtil.generateToken(userAuth);
                    ResponseCookie cookie = ResponseCookie.from("jwt", token)
                            .httpOnly(true)
                            .secure(true)
                            .path("/")
                            .maxAge(Duration.ofHours(1))
                            .sameSite("None")
                            .build();
                    response.addCookie(cookie);
                    return ResponseEntity.ok(new ResponseAuth(token));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserAuth>> register(@RequestBody RequestAuth requestAuth) {
        return Mono.just(ResponseEntity.ok(userService.register(requestAuth.getUsername(), requestAuth.getPassword())));
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(ServerHttpResponse response) {
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .maxAge(0)
                .path("/")
                .build();
        response.addCookie(cookie);
        return Mono.just(ResponseEntity.ok().build());
    }
}
