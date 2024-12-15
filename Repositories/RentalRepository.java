package Book.demo.Repositories;

import Book.demo.Entity.Rental;
import Book.demo.Entity.User;
import Book.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByBook_BookId(Long bookId);
    List<Rental> findByUserUserId(int userId);
}

