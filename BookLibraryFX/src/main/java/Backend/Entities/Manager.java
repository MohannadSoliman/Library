package Backend.Entities;


import Backend.DTOs.BookDTO;
import Backend.DTOs.UserDTO;
import Backend.SQLCommands.BookSQL;
import Backend.SQLCommands.OrdersSQL;
import Backend.SQLCommands.StatisticsSQL;
import Backend.SQLCommands.UserSQL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager extends User{
    public Manager(UserDTO userDTO) {
        super(userDTO);
    }

    public boolean addNewBook(BookDTO bookDTO){
        BookSQL.addBook(bookDTO);
        return true;
    }
    public boolean modifyExistingBook(String isbn, String target, String attribute){
        // use the update command in the sql commands
        BookSQL.updateBook(isbn, target, attribute);
        return true;
    }
    public boolean placeOrder(String isbn, int quantity){
        // check if book already exists before ordering extra
        ArrayList<Book> books = BookSQL.getBook(isbn, "isbn");
        if(books == null) return false;

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        Order order = new Order(0, Integer.parseInt(isbn), quantity, date);
        // then use sql command with the order
        return OrdersSQL.addOrder(order);
    }
    public boolean confirmOrder(int orderID){
        // use sql command order confirmation
        return OrdersSQL.confirmOrder(orderID);
    }
    public boolean promoteUser(String username){
        // use the sql command for upgrading
        return UserSQL.promote(username);
    }
    public ArrayList<Purchase> getTotalSales(){
        // get total sales sql command
        return StatisticsSQL.getTotalSales();
    }
    public ArrayList<BookSale> getTopSellingBooks(){
        // use sql command to get the books
        return StatisticsSQL.getTopTenBooks();
    }
    public ArrayList<TopUser> getTopUsers(){
        return StatisticsSQL.getTopFiveUsers();
    }

}
