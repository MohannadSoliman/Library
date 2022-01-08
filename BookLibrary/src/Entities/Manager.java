package Entities;

import DTOs.UserDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager extends User{
    public Manager(UserDTO userDTO) {
        super(userDTO);
    }

    public boolean addNewBook(Book book){
        // add new book using sql
        return true;
    }
    public boolean modifyExistingBook(int isbn, String target, String attribute){
        // use the update command in the sql commands
        return true;
    }
    public boolean placeOrder(int isbn, int quantity){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        Order order = new Order(0, isbn, quantity, date);
        // then use sql command with the order
        return true;
    }
    public boolean confirmOrder(int orderID){
        // use sql command order confirmation
        return true;
    }
    public boolean promoteUser(String username){
        // use the sql command for upgrading
        return true;
    }
    public double totalSales(){
        // get total sales sql command
        return 0;
    }
    public ArrayList<Book> viewTopSellingBooks(){
        // use sql command to get the books
        return null;
    }

}
