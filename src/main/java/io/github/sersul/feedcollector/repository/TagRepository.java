package io.github.sersul.feedcollector.repository;

import io.github.sersul.feedcollector.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
