package io.github.sersul.feedcollector.repository;

import io.github.sersul.feedcollector.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
