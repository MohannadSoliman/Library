package SQLCommands;

import DTOs.BookDTO;
import DTOs.UserDTO;
import Entities.Book;
import Entities.Manager;
import Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookSQL {
    public static boolean addBook(BookDTO bookDTO){
        try {
            String query = "insert into book values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, String.valueOf(bookDTO.isbn));
            ps.setString(2, String.valueOf(bookDTO.threshold));
            ps.setString(3, String.valueOf(bookDTO.quantity));
            ps.setString(4, bookDTO.title);
            ps.setString(5, bookDTO.publisher);
            ps.setString(6, bookDTO.publicationYear);
            ps.setString(7, String.valueOf(bookDTO.price));
            ps.setString(8, bookDTO.category);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static boolean updateBook(String isbn, String target, String attribute){
        try {
            String query = "update book set " + attribute + " = (?) where isbn = (?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, target);
            ps.setString(2, isbn);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static ArrayList<Book> getBook(String target, String attribute){
        ArrayList<Book> books = new ArrayList<>();
        String query = "select * from book where "+attribute+" = (?)";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, target);
            ResultSet response = ps.executeQuery();
            while(response.next()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.fromSQLtoBook(response);
                Book book = new Book(bookDTO);
                // from shehab's work
                book.setAuthors(AuthorSQL.getAuthorList(book.getIsbn()));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        String query = "select * from book";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);

            ResultSet response = ps.executeQuery();
            while(response.next()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.fromSQLtoBook(response);
                Book book = new Book(bookDTO);
                // from shehab's work
                book.setAuthors(AuthorSQL.getAuthorList(book.getIsbn()));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
