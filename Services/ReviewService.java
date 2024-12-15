package Book.demo.Services;

import Book.demo.Entity.Review;
import Book.demo.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBook_BookId(bookId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUser_UserId(userId);
    }

    public Review addReview(Review review) {
        try {
            return reviewRepository.save(review);
        } catch (Exception e) {
            logger.error("Error adding review", e);
            throw e;
        }
    }

    public Review updateReview(Long reviewId, Review reviewDetails) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setRating(reviewDetails.getRating());
            review.setReviewText(reviewDetails.getReviewText());
            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
