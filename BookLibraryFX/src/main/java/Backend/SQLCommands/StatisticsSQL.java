package Backend.SQLCommands;

import Backend.DTOs.BookSaleDTO;
import Backend.DTOs.PurchaseDTO;
import Backend.DTOs.TopUserDTO;
import Backend.Entities.BookSale;
import Backend.Entities.Purchase;
import Backend.Entities.TopUser;

import java.sql.*;
import java.util.ArrayList;

public class StatisticsSQL {

    //ask the guys what is required exactly in this function , then edit if needed

    public static ArrayList<Purchase> getTotalSales(){
        ArrayList<Purchase> lastMonthSales = new ArrayList<>();
        String query = "Select purchase_id, book_purchase.isbn, username, book_purchase.quantity,"
                + " purchase_price, purchase_date from book_purchase join book on"
                + " (book_purchase.isbn = book.isbn) where"
                + " book_purchase.purchase_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH);";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ResultSet response = ps.executeQuery();
            while(response.next()){
                PurchaseDTO PurchaseDTO = new PurchaseDTO();
                PurchaseDTO.fromSQLtoPurchase(response);
                Purchase purchase = new Purchase(PurchaseDTO);
                lastMonthSales.add(purchase);
            }
            return lastMonthSales;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<TopUser> getTopFiveUsers(){
        ArrayList<TopUser> lastThreeMonthsTopUsers = new ArrayList<>();
        String query = "Select users.username, users.user_password, users.first_name, users.last_name,users.address ,"
       + "users.email, users.phone , users.user_role, sum(book_purchase.quantity) from book_purchase join users on"
             +   " (book_purchase.username = users.username) where"
      +  " book_purchase.purchase_date >= DATE_SUB(NOW(), INTERVAL 3 MONTH) group by book_purchase.username"
                +  " order by sum(book_purchase.quantity) DESC LIMIT 5;";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ResultSet response = ps.executeQuery();
            while(response.next()){
                TopUserDTO topUserDTO = new TopUserDTO();
                topUserDTO.fromSQLtoTopUser(response);
                TopUser topUser = new TopUser(topUserDTO);
                lastThreeMonthsTopUsers.add(topUser);
            }
            return lastThreeMonthsTopUsers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<BookSale> getTopTenBooks(){
        ArrayList<BookSale> lastThreeMonthsTopBooks = new ArrayList<>();
        String query = "Select book.isbn , book.title,book.publisher,book.publication_year,book.price,book.category"
               +" ,sum(book_purchase.quantity) from book_purchase join book on"
              +" (book_purchase.isbn = book.isbn) where"
       +" book_purchase.purchase_date >= DATE_SUB(NOW(), INTERVAL 3 MONTH) group by book.isbn"
       +" order by sum(book_purchase.quantity) DESC LIMIT 10;";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ResultSet response = ps.executeQuery();
            while(response.next()){
                BookSaleDTO bookSaleDTO = new BookSaleDTO();
                bookSaleDTO.fromSQLtoBookSale(response);
                BookSale bookSale = new BookSale(bookSaleDTO);
                lastThreeMonthsTopBooks.add(bookSale);
            }
            return lastThreeMonthsTopBooks;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws SQLException {

        ArrayList<Purchase> lastMonthSales = getTotalSales();
        for (int i = 0; i < lastMonthSales.size(); i++){
            System.out.println("Purchase" + (i+1) + "\n");
            System.out.println("id : " +lastMonthSales.get(i).getId() );
            System.out.println("isbn : " +lastMonthSales.get(i).getIsbn() );
            System.out.println("username : " +lastMonthSales.get(i).getUsername() );
            System.out.println("quantity : " +lastMonthSales.get(i).getQuantity());
            System.out.println("purchase_price : " +lastMonthSales.get(i).getPurchasePrice());
            System.out.println("purchase_date : " +lastMonthSales.get(i).getPurchaseDate() + "\n");
        }

//        ArrayList<TopUser> lastThreeMonthsTopFive = getTopFiveUsers();
//        for (int i = 0; i < lastThreeMonthsTopFive.size(); i++){
//            System.out.println("User" + (i+1) + "\n");
//            System.out.println("username : " +lastThreeMonthsTopFive.get(i).getUsername() );
//            System.out.println("password : " +lastThreeMonthsTopFive.get(i).getPassword() );
//            System.out.println("firstname : " +lastThreeMonthsTopFive.get(i).getFirstName() );
//            System.out.println("lastname : " +lastThreeMonthsTopFive.get(i).getLastName());
//            System.out.println("address : " +lastThreeMonthsTopFive.get(i).getAddress());
//            System.out.println("email : " +lastThreeMonthsTopFive.get(i).getEmail());
//            System.out.println("phone : " +lastThreeMonthsTopFive.get(i).getPhone());
//            System.out.println("role : " +lastThreeMonthsTopFive.get(i).getRole() );
//            System.out.println("quantityPurchased : " +lastThreeMonthsTopFive.get(i).getQuantityPurchased() + "\n");

//            ArrayList<BookSale> lastThreeMonthsTopTen = getTopTenBooks();
//            for (int i = 0; i < lastThreeMonthsTopTen.size(); i++){
//                System.out.println("Book" + (i+1) + "\n");
//                System.out.println("isbn : " +lastThreeMonthsTopTen.get(i).getIsbn() );
//                System.out.println("title : " +lastThreeMonthsTopTen.get(i).getTitle() );
//                System.out.println("publisher : " +lastThreeMonthsTopTen.get(i).getPublisher() );
//                System.out.println("publicationYear : " +lastThreeMonthsTopTen.get(i).getPublicationYear());
//                System.out.println("price : " +lastThreeMonthsTopTen.get(i).getPrice());
//                System.out.println("category : " +lastThreeMonthsTopTen.get(i).getCategory());
//                System.out.println("totalSales : " +lastThreeMonthsTopTen.get(i).getTotalSales() + "\n");

        }
    }

