package Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phone;
    private ArrayList<CartItem> cart = new ArrayList<>();

    public User(String username, String firstName, String lastName, String address, String password, String email, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public String getUsername() {
        return username;
    }
    // loop thru the cart items and sum each total
    public double getCartPrice(){
        double sum = 0;
        for(CartItem ci : cart){
            sum += ci.getTotalPrice();
        }
        return sum;
    }
    public boolean editUser(String target, String attribute){
        // call the sql command in UserSQL
        // then return true if everything worked
        // else return false
        return true;
    }
    public ArrayList<Book> search(String target, String attribute){
        // sql command to return books and create entities and fill each with book content
        return null;
    }
    public boolean addBook(String isbn){
        // look for book then create book instance then insert into cart as cart item
        return true;
    }
    public double viewIndividualPrice(int isbn){
        double result = 0;
        for(CartItem ci : cart){
            if(ci.getIsbn() == isbn) {
                result = ci.getSinglePrice();
                break;
            }
        }
        return result;
    }
    public double viewItemTotalPrice(int isbn){
        double result = 0;
        for(CartItem ci : cart){
            if(ci.getIsbn() == isbn) {
                result = ci.getTotalPrice();
                break;
            }
        }
        return result;
    }
}
