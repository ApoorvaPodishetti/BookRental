package Book.demo.Repositories;

import Book.demo.Entity.Reminder;
import Book.demo.Entity.User;
import Book.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByUser(User user);
    List<Reminder> findByBook(Book book);
}

