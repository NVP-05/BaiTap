package ra.edu.ss15.service;

import ra.edu.ss15.model.dto.entity.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review, Long userId);
    List<Review> getReviewsByProduct(Long productId);
}

