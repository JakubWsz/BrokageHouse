package com.brokagehouse.authorizationservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "auth_user"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthUser authUser = (AuthUser) o;
        return id != null && Objects.equals(id, authUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}