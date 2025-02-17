package Book.demo.Repositories;

import Book.demo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook_BookId(Long bookId);
    List<Review> findByUser_UserId(Long userId);
}
