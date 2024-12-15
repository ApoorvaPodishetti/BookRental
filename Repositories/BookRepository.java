package Book.demo.Repositories;
import Book.demo.Entity.Book;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByTitleContainingOrGenreContaining(String title, String genre);
    List<Book> findByGenreIgnoreCase(String genre);

    List<Book> findByAuthorContainingIgnoreCase(String author);
    @Query("SELECT b.availableCopies FROM Book b WHERE b.id = :bookId") int findAvailableCopiesById(Long bookId);
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.availableCopies = :availableCopies WHERE b.id = :bookId")
    void updateAvailableCopies(Long bookId, int availableCopies);

    Optional<Book> findByTitleAndAuthor(String title, String author);
}
