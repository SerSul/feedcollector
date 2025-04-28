package io.github.sersul.feedcollector.repository;

import io.github.sersul.feedcollector.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    void save(Review review);

    List<Review> findAllByTitle(String title);

    List<Review> findAllByUserName(String userName);

    Optional<Review> findById(Long id);

    List<Review> findAll();

    Page<Review> findAllByUserName(String userName, Pageable pageable);

    Page<Review> findAllByTitle(String title, Pageable pageable);

    List<Review> findByTitleContainingIgnoreCase(String title);
}
