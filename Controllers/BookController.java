package Book.demo.Controllers;

import Book.demo.Entity.Book;
import Book.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String searchTerm) {
        return bookService.searchBooks(searchTerm);
    }

    @GetMapping("/filter")
    public List<Book> filterBooksByGenre(@RequestParam String genre) {
        return bookService.filterBooksByGenre(genre);
    }

    @GetMapping("/{bookId}")
    public Optional<Book> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/author")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
    @GetMapping("/popular")
    public List<Book> getPopularBooks() {
        return bookService.getPopularBooks();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(bookId, bookDetails);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
