package com.example.booklibraryfx;

import Backend.Entities.Book;
import Backend.SQLCommands.BookSQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerMainController implements Initializable {
    @FXML
    VBox searchResults;
    @FXML
    TextField searchKey;
    @FXML
    ComboBox<String> attribute;
    @FXML
    AnchorPane reportPop;
    @FXML
    Button popCancelBtn;

    public void switchToEditProfile() throws IOException {
        Settings.stage.setScene(Settings.editUser);
        Settings.stage.setTitle("Edit Profile");
        Settings.stage.show();
    }

    public void searchBooks() {
        List<Book> result = BookSQL.getBook(searchKey.getText(), attribute.getValue());
        searchKey.setText("");
        if(result == null) result = new ArrayList<>();
        viewSearchResults(result);
    }

    private void viewSearchResults(List<Book> books) {
        for(Book book: books) {
            searchResults.getChildren().add(addBookCard(book));
        }
    }

    private AnchorPane addBookCard(Book book) {
        var card = createParentCard();
        var isbnLabel = new Label("ISBN: " + Integer.toString(book.getIsbn()));
        isbnLabel.setLayoutX(320);
        isbnLabel.setLayoutY(5);
        isbnLabel.setPrefWidth(150);
        isbnLabel.setPrefHeight(20);

        var titleLabel = new Label("Title: " + book.getTitle());
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(5);
        titleLabel.setPrefWidth(150);
        titleLabel.setPrefHeight(20);

        var publisherLabel = new Label("Publisher: " + book.getPublisher());
        publisherLabel.setLayoutX(10);
        publisherLabel.setLayoutY(35);
        publisherLabel.setPrefWidth(150);
        publisherLabel.setPrefHeight(20);

        var categoryLabel = new Label("Category: " + book.getCategory());
        categoryLabel.setLayoutX(320);
        categoryLabel.setLayoutY(35);
        categoryLabel.setPrefWidth(100);
        categoryLabel.setPrefHeight(20);

        var authors = new Label("Authors: " + convertAuthorsToStr(book.getAuthors()));
        authors.setLayoutX(10);
        authors.setLayoutY(65);
        authors.setPrefWidth(200);
        authors.setPrefHeight(20);

        var price = new Label("Price: " + book.getPrice());
        price.setLayoutX(320);
        price.setLayoutY(65);
        price.setPrefWidth(70);
        price.setPrefHeight(20);

        var quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setLayoutX(550);
        quantityField.setLayoutY(50);
        quantityField.setPrefHeight(30);
        quantityField.setPrefWidth(100);

        var addToCartBth = new Button("Add to Cart");
        addToCartBth.setLayoutX(550);
        addToCartBth.setLayoutY(10);
        addToCartBth.setPrefHeight(30);
        addToCartBth.setPrefWidth(100);
        addToCartBth.setOnAction(e -> {
            if(quantityField.getText().trim().equals("")) return;
            int quantity = Integer.parseInt(quantityField.getText());
            if(quantity == 0) return;
            int isbn = book.getIsbn();
            //call function to add to carts
        });

        card.getChildren().add(isbnLabel);
        card.getChildren().add(titleLabel);
        card.getChildren().add(publisherLabel);
        card.getChildren().add(categoryLabel);
        card.getChildren().add(price);
        card.getChildren().add(authors);
        card.getChildren().add(addToCartBth);
        card.getChildren().add(quantityField);
        return card;
    }

    private AnchorPane createParentCard() {
        AnchorPane card = new AnchorPane();
        card.getStyleClass().add("list-card");
        card.setPrefHeight(80);
        card.setPrefWidth(700);
        return card;
    }

    private String convertAuthorsToStr(List<String> authors) {
        StringBuilder authorsStr = new StringBuilder();
        for(int i = 0; i < Math.min(3, authors.size()); i++) {
            authorsStr.append(authors.get(i)).append(" - ");
        }
        if(authors.size() > 3) authorsStr.append("...");
        return authorsStr.toString();
    }

    public void showReportDialog() {
        reportPop.setVisible(true);
    }

    public void closePopUp() {
        reportPop.setVisible(false);
    }

    public void showTotalSales() {
    }

    public void showTop5Customers() {
    }

    public void showTop10SellingBooks() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attribute.getItems().removeAll(attribute.getItems());
        attribute.getItems().addAll("Title", "ISBN", "Publisher", "Author", "Category");
        attribute.getSelectionModel().select("Title");
        reportPop.setVisible(false);
    }
}
