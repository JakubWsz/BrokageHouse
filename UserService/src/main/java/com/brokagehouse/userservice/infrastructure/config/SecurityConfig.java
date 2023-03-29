package com.brokagehouse.userservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.mvcMatcher("/**")
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user/create") // Endpoint POST /user/create będzie wykluczony z zabezpieczeń
//                .authenticated()
//                .mvcMatchers("/**")
//                .access("hasAuthority('SCOPE_message.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/user/home").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
//				.requestMatchers("/web/app/**").authenticated()
//				.and()
//				.formLogin().loginPage("/web/login/login-page")
//				.loginProcessingUrl("/authenticateUser")
//				.defaultSuccessUrl("/web/app/dashboard")
//				.permitAll()
//				.and()
//				.logout()
//				.logoutSuccessUrl("/web/login/login-page?logout")
//				.permitAll().and().build();

//        return http.build();
    }
}
