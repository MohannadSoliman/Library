package Backend.DTOs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSaleDTO {
    public int isbn;
    public String title;
    public String publisher;
    public String publicationYear;
    public double price;
    public String category;
    public int totalSales;

    public void fromSQLtoBookSale(ResultSet rs){
        try {
            this.isbn = Integer.parseInt(rs.getString(1));
            this.title = rs.getString(2);
            this.publisher = rs.getString(3);
            this.publicationYear = rs.getString(4);
            this.price = Double.parseDouble(rs.getString(5));
            this.category = rs.getString(6);
            this.totalSales = Integer.parseInt(rs.getString(7));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
