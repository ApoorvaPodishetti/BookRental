package Book.demo.Controllers;

import Book.demo.Entity.Review;
import Book.demo.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.status(201).body(savedReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review reviewDetails) {
        Review updatedReview = reviewService.updateReview(reviewId, reviewDetails);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
