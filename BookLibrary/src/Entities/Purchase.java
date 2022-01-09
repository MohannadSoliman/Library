package Entities;

import DTOs.PurchaseDTO;

public class Purchase {
    private int id;
    private int isbn;
    private String username;
    private int quantity;
    private double purchasePrice;
    private String purchaseDate;

    public Purchase(PurchaseDTO PurchaseDTO) {
        this.id = PurchaseDTO.id;
        this.isbn = PurchaseDTO.isbn;
        this.username = PurchaseDTO.username;
        this.quantity = PurchaseDTO.quantity;
        this.purchasePrice = PurchaseDTO.purchasePrice;
        this.purchaseDate = PurchaseDTO.purchaseDate;
    }

    public int getId() {
        return id;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getUsername() {
        return username;
    }

    public int getQuantity() { return quantity; }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
