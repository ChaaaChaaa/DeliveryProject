package com.programmers.repository.review;

import com.programmers.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(@Nullable Long reviewId);
    Optional<Review> findByStore(@Nullable Long reviewId); //메뉴 테이블에 있는데 findByStore하는게 맞는가?

    Review save(@Nullable Review review);

    void delete(@Nullable Review review);
}
