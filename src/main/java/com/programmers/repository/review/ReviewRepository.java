package com.programmers.repository.review;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.programmers.domain.review.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewId(@Nullable Long reviewId);

	Review save(@Nullable Review review);

	void delete(@Nullable Review review);
}
