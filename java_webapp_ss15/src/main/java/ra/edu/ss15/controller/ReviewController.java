package ra.edu.ss15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss15.model.dto.entity.Review;
import ra.edu.ss15.service.ReviewService;
import ra.edu.ss15.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        Long userId = userService.getCurrentUser().getUserId()  ;
        return ResponseEntity.ok(reviewService.addReview(review, userId));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(id));
    }
}

