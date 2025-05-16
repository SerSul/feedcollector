package io.github.sersul.feedcollector.repository;

import io.github.sersul.feedcollector.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
