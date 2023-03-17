//package com.brokagehouse.authorizationservice.entity;
//
//
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import javax.persistence.*;
//
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "auth_user_roles")
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class AuthUserRole {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    AuthUser user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", nullable = false)
//    Role role;
//}