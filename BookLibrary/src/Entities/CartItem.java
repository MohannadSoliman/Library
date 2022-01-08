package Entities;

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
}
