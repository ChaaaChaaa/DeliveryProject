package com.programmers.repository.review;

import com.programmers.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(@Nullable Long reviewId);
    Optional<Review> findStoreByReviewId(@Nullable Long reviewId);

    Review save(@Nullable Review review);

    void delete(@Nullable Review review);
}
