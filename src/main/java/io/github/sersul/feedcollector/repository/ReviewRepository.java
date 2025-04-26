package io.github.sersul.feedcollector.repository;

import io.github.sersul.feedcollector.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    List<Review> findAllByTitle(String title);

    List<Review> findAllByUserName(String userName);

    List<Review> findAll();

    Page<Review> findAllByUserName(String userName, Pageable pageable);

    Page<Review> findAllByTitle(String title, Pageable pageable);

    List<Review> findByTitleContainingIgnoreCase(String title);
}
