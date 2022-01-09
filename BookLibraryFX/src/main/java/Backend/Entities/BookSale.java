package Backend.Entities;

import Backend.DTOs.BookSaleDTO;

public class BookSale {

    public int isbn;
    public String title;
    public String publisher;
    public String publicationYear;
    public double price;
    public String category;
    public int totalSales;

    public BookSale(BookSaleDTO bookSaleDTO) {
        this.isbn = bookSaleDTO.isbn;
        this.title = bookSaleDTO.title;
        this.publisher = bookSaleDTO.publisher;
        this.publicationYear = bookSaleDTO.publicationYear;
        this.price = bookSaleDTO.price;
        this.category = bookSaleDTO.category;
        this.totalSales = bookSaleDTO.totalSales;
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

    public String getPublicationYear() {
        return publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalSales() {
        return totalSales;
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

    public void setPublicationYear(String publicationYear) {this.publicationYear = publicationYear;}

    public void setPrice(double price) {this.price = price;}

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
}
