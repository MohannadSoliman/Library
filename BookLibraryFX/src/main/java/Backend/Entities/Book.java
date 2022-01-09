package Backend.Entities;

import Backend.DTOs.BookDTO;

import java.util.ArrayList;

public class Book {
    private int isbn;
    private String title;
    private String publisher;
    private ArrayList<String> authors;
    private String PublicationYear;
    private double price;
    private String category;
    private int quantity;

    public Book(BookDTO bookDTO) {
        this.isbn = bookDTO.isbn;
        this.title = bookDTO.title;
        this.publisher = bookDTO.publisher;
        this.authors = bookDTO.authors;
        this.PublicationYear = bookDTO.publicationYear;
        this.price = bookDTO.price;
        this.category = bookDTO.category;
        this.quantity = bookDTO.quantity;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getPublicationYear() {
        return PublicationYear;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public void setPublicationYear(String publicationYear) {
        PublicationYear = publicationYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
