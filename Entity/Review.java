package Book.demo.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"username", "fullName", "password", "phoneNumber", "email", "role", "createdAt", "rentals", "reviews"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"title", "author", "genre", "description", "publicationYear", "availableCopies", "totalCopies", "averageRating", "reviews"})
    private Book book;

    private int rating;
    private String reviewText;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
