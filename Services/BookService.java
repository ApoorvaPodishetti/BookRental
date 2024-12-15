package Book.demo.Services;

import Book.demo.Entity.Book;
import Book.demo.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.findByTitleContainingOrGenreContaining(searchTerm, searchTerm);
    }

    public List<Book> filterBooksByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    public List<Book> getPopularBooks() {
        return bookRepository.findAll().stream().filter(book -> book.getReviews().size() > 0).sorted((b1, b2) -> Double.compare(b2.getAverageRating(), b1.getAverageRating())).collect(Collectors.toList());
    }

    @Transactional
    public Book saveBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            throw new RuntimeException("Book already exists");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setDescription(bookDetails.getDescription());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setAvailableCopies(bookDetails.getAvailableCopies());
            book.setTotalCopies(bookDetails.getTotalCopies());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
