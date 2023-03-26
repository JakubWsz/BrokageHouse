package com.brokagehouse.authservice.config;

import com.brokagehouse.authservice.entities.AuthUser;
import com.brokagehouse.authservice.exception.AuthExceptionCode;
import com.brokagehouse.authservice.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAuthenticationProvider implements AuthenticationProvider {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthUser user = userRepository.findByLogin(authentication.getName())
                .orElseThrow(AuthExceptionCode.INVALID_CREDENTIALS::createValidationException);
        if (passwordEncoder.matches(CharBuffer.wrap(authentication.getCredentials().toString()), user.getPassword()))
            return UsernamePasswordAuthenticationToken.authenticated(
                    user.getLogin(), user.getPassword(), Collections.emptyList()); //TODO: check password
        else
            throw AuthExceptionCode.INVALID_CREDENTIALS.createValidationException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
