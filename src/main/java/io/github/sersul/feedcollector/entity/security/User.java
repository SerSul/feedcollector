package io.github.sersul.feedcollector.entity.security;

import io.github.sersul.feedcollector.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "user_name_p", nullable = false, unique = true)
     String username;

    @Column(name = "email_p", nullable = false, unique = true)
     String email;

    @Column(name = "password_p", nullable = false)
     String password;

    @Column(name = "is_active", nullable = false)
     boolean isActive = true;

    @Column(name = "is_locked", nullable = false)
     boolean isLocked = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
