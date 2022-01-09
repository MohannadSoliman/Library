package SQLCommands;
import Entities.Author;
import Entities.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherSQL {

    static public boolean addPublisher(Publisher publisher){
        ConnectorSQL connection = new ConnectorSQL();
        if (publisher == null){
            return false;
        }
        try {
            String query = " insert into publisher (publisher_name, phone, address)" + " values (?, ?, ?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setString(2, publisher.getPhone());
            preparedStatement.setString(3, publisher.getAddress());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    static public Publisher getPublisher(String name){
        ConnectorSQL connection = new ConnectorSQL();
        String phone = "";
        String address = "";
        if (name.length() == 0){
            return null;
        }
        try {

            String query = "SELECT * FROM publisher where publisher_name = (?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                phone = result.getString(2);
                address = result.getString(3);
            }else{
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return new Publisher(name, phone, address);
    }

    static public boolean updatePublisher(String publisherName, Publisher newPublisher){
        ConnectorSQL connection = new ConnectorSQL();
        try {
            String queryString = "update publisher set publisher_name = (?) , address = (?) , phone = (?)"
                    + " where publisher_name = (?)";
            PreparedStatement preparedStmt = ConnectorSQL.connection.prepareStatement(queryString);
            preparedStmt.setString(1, newPublisher.getName());
            preparedStmt.setString(2, newPublisher.getAddress());
            preparedStmt.setString(3, newPublisher.getPhone());
            preparedStmt.setString(4, publisherName);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        Publisher shehab = new Publisher("shehaaaaaaaaab", "0123456", "kosomaha f tizha street");
//        addPublisher(shehab);
//        Publisher p = getPublisher("The LOL publisher");
        Publisher shehab = new Publisher("shehab", "012345678", "kosomaha f tizha street");
        updatePublisher("shehaaaaaaaaab", shehab);
        System.out.println("hi");
    }
}
