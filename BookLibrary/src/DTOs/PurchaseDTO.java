package DTOs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseDTO {

    public int id;
    public int isbn;
    public String username;
    public int quantity;
    public double purchasePrice;
    public String purchaseDate;

    public void fromSQLtoPurchase(ResultSet rs){
        try {
            this.id = Integer.parseInt(rs.getString(1));
            this.isbn = Integer.parseInt(rs.getString(2));
            this.username = rs.getString(3);
            this.quantity = Integer.parseInt(rs.getString(4));
            this.purchasePrice = Double.parseDouble(rs.getString(5));
            this.purchaseDate = rs.getString(6);
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}


