package Book.demo.Services;

import Book.demo.Entity.Rental;
import Book.demo.Entity.User;
import Book.demo.Entity.Book;
import Book.demo.Entity.RentalRequest;
import Book.demo.Repositories.BookRepository;
import Book.demo.Repositories.RentalRepository;
import Book.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean rentBook(RentalRequest rentalRequest) {
        Optional<Book> book = bookRepository.findById(rentalRequest.getBookId());
        Optional<User> user = userRepository.findById(rentalRequest.getUserId());

        if (book.isPresent() && user.isPresent()) {
            Book rentedBook = book.get();
            if (rentedBook.getAvailableCopies() > 0) {
                Rental rental = new Rental();
                rental.setUser(user.get());
                rental.setBook(rentedBook);
                rental.setRentalStartDate(LocalDate.parse(rentalRequest.getRentalStartDate()));
                rental.setRentalEndDate(LocalDate.parse(rentalRequest.getRentalEndDate()));
                rental.setRentalStatus(Rental.RentalStatus.RENTED);

                rentalRepository.save(rental);

                rentedBook.setAvailableCopies(rentedBook.getAvailableCopies() - 1);
                bookRepository.save(rentedBook);

                return true;
            } else {
                System.out.println("No available copies");
            }
        } else {
            System.out.println("Error finding book or user");
        }
        return false;
    }


}
