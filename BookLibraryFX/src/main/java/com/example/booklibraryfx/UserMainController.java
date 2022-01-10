package com.example.booklibraryfx;

import Backend.DTOs.BookDTO;
import Backend.Entities.Book;
import Backend.Entities.CartItem;
import Backend.Entities.Order;
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

public class UserMainController implements Initializable {
    @FXML
    VBox searchResults;
    @FXML
    TextField searchKey;
    @FXML
    ComboBox<String> attribute;
    @FXML
    Button cartCancel;
    @FXML
    AnchorPane cartWindow;
    @FXML
    VBox cartList;
    @FXML
    Label totalPrice;
    @FXML
    Button viewCartItemsBtn;

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
        searchResults.getChildren().clear();
        for(Book book: books) {
            searchResults.getChildren().add(addBookCard(book));
        }
    }

    public void viewCartItems() {
        cartList.getChildren().clear();
        showCartItemsDialog();

        List<CartItem> cartItems= new ArrayList<>();

        if(cartItems == null) cartItems = new ArrayList<>();
        for(CartItem cartItem: cartItems) {
            cartList.getChildren().add(addCartItemCard(cartItem));
        }

        totalPrice.setText(""); //add total checkout price function here
    }

    public void checkout() {
        // add checkout action here
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

        var totalQuantity = new Label("Quantity: " + book.getQuantity());
        totalQuantity.setLayoutX(550);
        totalQuantity.setLayoutY(55);
        totalQuantity.setPrefHeight(20);
        totalQuantity.setPrefWidth(100);

        var quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setLayoutX(550);
        quantityField.setLayoutY(30);
        quantityField.setPrefHeight(20);
        quantityField.setPrefWidth(100);

        var addToCartBth = new Button("Add to Cart");
        addToCartBth.setLayoutX(550);
        addToCartBth.setLayoutY(5);
        addToCartBth.setPrefHeight(20);
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

    public AnchorPane addCartItemCard(CartItem cartItem) {
        AnchorPane card = new AnchorPane();
        card.getStyleClass().add("list-card");
        card.setPrefHeight(40);
        card.setPrefWidth(700);

        var bookTitle = new Label("Order Id: " + cartItem.getIsbn());
        bookTitle.setLayoutX(10);
        bookTitle.setLayoutY(10);
        bookTitle.setPrefWidth(100);
        bookTitle.setPrefHeight(20);

        var isbnLabel = new Label("ISBN: " + cartItem.getIsbn());
        isbnLabel.setLayoutX(140);
        isbnLabel.setLayoutY(10);
        isbnLabel.setPrefWidth(150);
        isbnLabel.setPrefHeight(20);

        var singlePrice = new Label("price: " + cartItem.getSinglePrice());
        singlePrice.setLayoutX(260);
        singlePrice.setLayoutY(10);
        singlePrice.setPrefWidth(80);
        singlePrice.setPrefHeight(20);

        var quantityLabel = new Label("Quantity: " + cartItem.getQuantity());
        quantityLabel.setLayoutX(350);
        quantityLabel.setLayoutY(10);
        quantityLabel.setPrefWidth(80);
        quantityLabel.setPrefHeight(20);

        var totalPrice = new Label("Total price: " + cartItem.getTotalPrice());
        totalPrice.setLayoutX(450);
        totalPrice.setLayoutY(10);
        totalPrice.setPrefWidth(120);
        totalPrice.setPrefHeight(20);

        var removeBtn = new Button("Remove");
        removeBtn.setLayoutX(600);
        removeBtn.setLayoutY(5);
        removeBtn.setPrefHeight(30);
        removeBtn.setPrefWidth(80);
        removeBtn.setOnAction(e -> {
            int isbn = cartItem.getIsbn();
            //call function to remove cart item
        });

        card.getChildren().add(bookTitle);
        card.getChildren().add(isbnLabel);
        card.getChildren().add(singlePrice);
        card.getChildren().add(quantityLabel);
        card.getChildren().add(totalPrice);
        card.getChildren().add(removeBtn);
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

    private void showCartItemsDialog() {
        cartWindow.setVisible(true);
    }
    public void closeCartItemsDialog() {
        cartWindow.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attribute.getItems().removeAll(attribute.getItems());
        attribute.getItems().addAll("Title", "ISBN", "Publisher", "Author", "Category");
        attribute.getSelectionModel().select("Title");
        closeCartItemsDialog();
    }
}
