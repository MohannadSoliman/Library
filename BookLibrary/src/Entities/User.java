package Entities;

import DTOs.UserDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phone;
    private String role;
    private ArrayList<CartItem> cart = new ArrayList<>();

    public User(UserDTO userdto) {
        this.username = userdto.username;
        this.firstName = userdto.firstName;
        this.lastName = userdto.lastName;
        this.address = userdto.address;
        this.password = userdto.password;
        this.email = userdto.email;
        this.phone = userdto.phone;
        this.role = userdto.role;
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

    public String getRole() { return role; }
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
    public boolean removeItem(int isbn){
        for(int i=0; i< cart.size(); i++){
            if(cart.get(i).getIsbn() == isbn) {
                cart.remove(i);
                break;
            }
        }
        return true;
    }
    public boolean checkout(String ccnumber, String ccexpiry){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.YEAR)+1;
        String[]ccexpiryComponents = ccexpiry.split("[/-]");
        int ccYear = Integer.parseInt(ccexpiryComponents[0]);
        int ccMonth = Integer.parseInt(ccexpiryComponents[1]);
        // then validate year and number size
        // if valid then call Purchase.checkout()
        return true;
    }
}
