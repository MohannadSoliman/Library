package SQLCommands;

import DTOs.UserDTO;
import Entities.Manager;
import Entities.User;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserSQL {

    public static User login(String username, String password) {
        String query = "select * from users where username = (?) and user_password = (?)";
        try {
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet response = ps.executeQuery();
            if (!response.next()) {
                return null;
            }
            UserDTO userDTO = new UserDTO();
            userDTO.fromSQLtoUser(response);
            if (response.getString(8).equalsIgnoreCase("Manager")) {
                return new Manager(userDTO);
            }
            return new User(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean signup(UserDTO userDTO) {
        try {
            String query = "insert into users values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, userDTO.username);
            ps.setString(2, userDTO.password);
            ps.setString(3, userDTO.firstName);
            ps.setString(4, userDTO.lastName);
            ps.setString(5, userDTO.address);
            ps.setString(6, userDTO.email);
            ps.setString(7, userDTO.phone);
            ps.setString(8, "Customer");
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean promote(String username) {
        try {
            String query = "update users set user_role = (?) where username = (?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, "Manager");
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean update(String username, String target, String attribute) {
        try {
            String query = "update users set " + attribute + " = (?) where username = (?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            //ps.setString(1, attribute);
            ps.setString(1, target);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static User getUser(String username) {
        User user = null;
        try {
            String query = "select * from users where username = (?)";
            PreparedStatement ps = ConnectorSQL.connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet response = ps.executeQuery();
            if (!response.next()) {
                return null;
            }
            UserDTO userDTO = new UserDTO();
            userDTO.fromSQLtoUser(response);
            user = new User(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
