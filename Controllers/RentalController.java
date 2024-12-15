package Book.demo.Controllers;

import Book.demo.Entity.RentalRequest;
import Book.demo.Entity.Book;
import Book.demo.Services.BookService;
import Book.demo.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private BookService bookService;

    @PostMapping("/rent")
    public ResponseEntity<String> rentBook(@RequestBody RentalRequest rentalRequest) {
        System.out.println("Received RentalRequest: " + rentalRequest); // log the request
        boolean isRented = rentalService.rentBook(rentalRequest);
        if (isRented) {
            return ResponseEntity.status(201).body("Book rented successfully!");
        }
        return ResponseEntity.status(400).body("Failed to rent book. Please check availability.");
    }

    @PostMapping("/rentByTitleOrGenre")
    public ResponseEntity<String> rentBookByTitleOrGenre(@RequestBody RentalRequest rentalRequest) {
        List<Book> books = bookService.searchBooks(rentalRequest.getSearchTerm());
        if (books.isEmpty()) {
            return ResponseEntity.status(400).body("No books found matching the search term.");
        }
        Book bookToRent = books.get(0);
        rentalRequest.setBookId(bookToRent.getBookId());
        boolean isRented = rentalService.rentBook(rentalRequest);
        if (isRented) {
            return ResponseEntity.status(201).body("Book rented successfully!");
        } else {
            return ResponseEntity.status(400).body("Failed to rent book. Please check availability.");
        }
    }
}
