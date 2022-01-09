package Backend.Entities;

public class CartItem {
    private Book book;
    private int isbn;
    private int quantity;
    private double totalPrice;
    private double singlePrice;

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }
}
