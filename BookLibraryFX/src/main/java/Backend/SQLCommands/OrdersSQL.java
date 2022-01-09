package Backend.SQLCommands;
import Backend.Entities.Author;
import Backend.Entities.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersSQL {
    static public boolean addOrder(Order order){
        if (order.getQuantity() == 0){
            return false;
        }try {
            ConnectorSQL.connection.setAutoCommit(false);
            String query = " insert into book_order (isbn, quantity, purchase_date)" + " values (?, ?, ?)";
            PreparedStatement preparedStmt = ConnectorSQL.connection.prepareStatement(query);
          //  preparedStmt.setInt(1, order.getOrderID());
            preparedStmt.setInt(1, order.getIsbn());
            preparedStmt.setInt(2, order.getQuantity());
            preparedStmt.setString(3, order.getDate());
            preparedStmt.execute();
        }catch (SQLException throwables){
            try {
                ConnectorSQL.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throwables.printStackTrace();
            return false;
        }
        try {
            ConnectorSQL.connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    static public Order getOrder(int id){
        if (id == 0){
            return null;
        }
        int isbn = 0;
        int quantity = 0;
        String date = "";
        try {
            String query = "SELECT * FROM book_order where order_id = (?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                isbn = result.getInt(2);
                quantity = result.getInt(3);
                date = result.getString(4);
            }else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return new Order(id, isbn, quantity, date);
    }

    static public boolean confirmOrder(int id){
        try {
            ConnectorSQL.connection.setAutoCommit(false);
            String query = " call confirm_order_procedure(?)";
            PreparedStatement preparedStmt = ConnectorSQL.connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
        }catch (SQLException throwables){
            try {
                ConnectorSQL.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throwables.printStackTrace();
            return false;
        }
        try {
            ConnectorSQL.connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    static public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM book_order";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()){
                return null;
            }
            orders.add(new Order(result.getInt(1), result.getInt(2),
                    result.getInt(3), result.getString(4)));
            while (result.next()) {
                orders.add(new Order(result.getInt(1), result.getInt(2),
                                     result.getInt(3), result.getString(4)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return orders;
    }

    public static void main(String[] args) {
        Order order = new Order(12,1,30, "1999-09-09");
        addOrder(order);
//        List<Order> out = getAllOrders();
//        System.out.println(out);
        //confirmOrder(12);
        getOrder(1);
        System.out.println("hi");
    }
}
