package Book.demo.Controllers;

import Book.demo.Entity.Admin;
import Book.demo.Entity.Book;
import Book.demo.Services.AdminService;
import Book.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/admins")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.status(201).body(savedAdmin);
    }

    @PutMapping("/admins/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long adminId, @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(adminId, adminDetails);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.saveBook(book);
            return ResponseEntity.status(201).body("Book added successfully: " + savedBook.getTitle());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
