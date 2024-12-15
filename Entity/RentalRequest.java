package Book.demo.Entity;

public class RentalRequest {

    private Long userId;
    private Long bookId;
    private String rentalStartDate;
    private String rentalEndDate;
    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
}
