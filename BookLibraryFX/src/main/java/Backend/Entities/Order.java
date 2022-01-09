package Backend.Entities;

public class Order {
    private int orderID;
    private int isbn;
    private int quantity;
    private String Date;

    public Order(int orderID, int isbn, int quantity, String date) {
        this.orderID = orderID;
        this.isbn = isbn;
        this.quantity = quantity;
        Date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return Date;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        Date = date;
    }
}
