package SQLCommands;
import java.sql.*;


public class ConnectorSQL {
    public static Connection connection;
    public ConnectorSQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "{UR PASS}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
