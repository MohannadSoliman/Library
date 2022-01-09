package Backend.DTOs;

import Backend.Entities.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDTO {
    public int isbn;
    public int threshold;
    public int quantity;
    public String title;
    public String publisher;
    public String publicationYear;
    public double price;
    public String category;
    public ArrayList<String> authors;

    public void fromSQLtoBook(ResultSet rs){
        try {
            this.isbn = Integer.parseInt(rs.getString(1));
            this.threshold = Integer.parseInt(rs.getString(2));
            this.quantity = Integer.parseInt(rs.getString(3));
            this.title = rs.getString(4);
            this.publisher = rs.getString(5);
            this.publicationYear = rs.getString(6);
            this.price = Double.parseDouble(rs.getString(7));
            this.category = rs.getString(8);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
