package ra.edu.ss15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss15.model.dto.entity.Review;
import ra.edu.ss15.model.dto.entity.User;
import ra.edu.ss15.repository.OrderItemRepository;
import ra.edu.ss15.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired private OrderItemRepository orderItemRepo;

    @Override
    public Review addReview(Review review, Long userId) {
        boolean bought = orderItemRepo.existsByOrder_User_IdAndProduct_Id(userId, review.getProduct().getId());
        if (!bought) throw new RuntimeException("Bạn chưa mua sản phẩm này");

        review.setUser(new User(userId));
        review.setCreatedDate(LocalDateTime.now());
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepo.findByProductId(productId);
    }
}

