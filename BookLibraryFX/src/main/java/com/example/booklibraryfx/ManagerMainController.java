package com.example.booklibraryfx;

import Backend.DTOs.BookDTO;
import Backend.Entities.Book;
import Backend.Entities.CartItem;
import Backend.Entities.Order;
import Backend.SQLCommands.BookSQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML
    Button ordersListCancel;
    @FXML
    AnchorPane ordersWindow;
    @FXML
    VBox ordersList;
    @FXML
    ScrollPane orderListContainer;
    @FXML
    AnchorPane cartWindow;
    @FXML
    VBox cartList;
    @FXML
    Label totalPrice;
    @FXML
    Button viewCartItemsBtn;
    @FXML
    TextField creditCardNo;
    @FXML
    TextField expireDate;
    @FXML
    Button purchaseBtn;
    @FXML
    Label errorMsg;
    @FXML
    AnchorPane creditCardInfoWindow;
    @FXML
    Button cancelPurchase;

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

    public void viewOrders() {
        showOrderListDialog();
        ordersList.getChildren().clear();

        List<Order> orders = new ArrayList<>();

        if(orders == null) orders = new ArrayList<>();
        for(Order order: orders) {
            ordersList.getChildren().add(addOrderCard(order));
        }
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
        errorMsg.setText("");
        creditCardNo.setText("");
        expireDate.setText("");
        showCreditCardInfoDialog();
    }

    public void purchase() {
        String ccNo = creditCardNo.getText();
        String expD  = expireDate.getText();
        boolean isValid = false; // function here
        if(!isValid) {
            errorMsg.setText("Purchase Info are not correct!!");
        }
        else {
            closeCreditCardInfoDialog();
            cartList.getChildren().clear();
            totalPrice.setText("");
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

        var totalQuantity = new Label("Quantity: " + book.getQuantity());
        totalQuantity.setLayoutX(550);
        totalQuantity.setLayoutY(55);
        totalQuantity.setPrefHeight(20);
        totalQuantity.setPrefWidth(100);

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

    public AnchorPane addOrderCard(Order order) {
        AnchorPane card = new AnchorPane();
        card.getStyleClass().add("list-card");
        card.setPrefHeight(40);
        card.setPrefWidth(700);

        var orderIdLabel = new Label("Order Id: " + order.getIsbn());
        orderIdLabel.setLayoutX(10);
        orderIdLabel.setLayoutY(10);
        orderIdLabel.setPrefWidth(100);
        orderIdLabel.setPrefHeight(20);

        var isbnLabel = new Label("ISBN: " + order.getIsbn());
        isbnLabel.setLayoutX(150);
        isbnLabel.setLayoutY(10);
        isbnLabel.setPrefWidth(150);
        isbnLabel.setPrefHeight(20);

        var quantityLabel = new Label("Quantity: " + order.getQuantity());
        quantityLabel.setLayoutX(250);
        quantityLabel.setLayoutY(10);
        quantityLabel.setPrefWidth(70);
        quantityLabel.setPrefHeight(20);

        var dateLabel = new Label("Date: " + order.getDate());
        dateLabel.setLayoutX(350);
        dateLabel.setLayoutY(10);
        dateLabel.setPrefWidth(100);
        dateLabel.setPrefHeight(20);

        var confirmBtn = new Button("Confirm");
        confirmBtn.setLayoutX(550);
        confirmBtn.setLayoutY(5);
        confirmBtn.setPrefHeight(30);
        confirmBtn.setPrefWidth(100);
        confirmBtn.setOnAction(e -> {
            int orderId = order.getOrderID();
            //call function to confirm order
        });

        card.getChildren().add(orderIdLabel);
        card.getChildren().add(isbnLabel);
        card.getChildren().add(quantityLabel);
        card.getChildren().add(dateLabel);
        card.getChildren().add(confirmBtn);
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

    public void showReportDialog() {
        reportPop.setVisible(true);
    }
    public void closeReportPopUp() {
        reportPop.setVisible(false);
    }

    private void showOrderListDialog() {
        ordersWindow.setVisible(true);
    }
    public void closeOrdersListPopUp() {
        ordersWindow.setVisible(false);
    }

    private void showCartItemsDialog() {
        errorMsg.setText("");
        creditCardNo.setText("");
        expireDate.setText("");
        cartWindow.setVisible(true);
    }
    public void closeCartItemsDialog() {
        cartWindow.setVisible(false);
    }

    private void showCreditCardInfoDialog() {
        creditCardInfoWindow.setVisible(true);
    }
    public void closeCreditCardInfoDialog() {
        creditCardInfoWindow.setVisible(false);
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
        closeReportPopUp();
        closeOrdersListPopUp();
        closeCartItemsDialog();
        closeCreditCardInfoDialog();
    }
}
