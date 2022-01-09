package DTOs;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopUserDTO{
    public String username;
    public String firstName;
    public String lastName;
    public String address;
    public String password;
    public String email;
    public String phone;
    public String role;
    public int quantityPurchased;
    public void fromSQLtoTopUser(ResultSet rs){
        try {
            this.username = rs.getString(1);
            this.password = rs.getString(2);
            this.firstName = rs.getString(3);
            this.lastName = rs.getString(4);
            this.address = rs.getString(5);
            this.email = rs.getString(6);
            this.phone = rs.getString(7);
            this.role = rs.getString(8);
            this.quantityPurchased = Integer.parseInt(rs.getString(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
