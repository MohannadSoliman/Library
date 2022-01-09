package SQLCommands;
import Entities.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorSQL {
    static public boolean addAuthor(Author author){
    	ConnectorSQL connection = new ConnectorSQL();
    	if (author == null){
            return false;
        }
        try {
            String query = " insert into author (isbn, author_name)" + " values (?, ?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setInt(1, author.getIsbn());
            preparedStatement.setString(2, author.getName());
            preparedStatement.execute();
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    static public Author getAuthor(String authorName){
        ConnectorSQL connection = new ConnectorSQL();
        if (authorName.length() == 0){
            return null;
        }
        int isbn = 0;
        try {
            String query = "SELECT * FROM author where author_name = (?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setString(1, authorName);
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                isbn = result.getInt(1);
            }else{
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return new Author(isbn, authorName);
    }

    static public List<String> getAuthorList(int isbn){
        ConnectorSQL connection = new ConnectorSQL();
        List<String> authors = new ArrayList<>();
        if (isbn == 0){
            return null;
        }
        try {
            String query = "SELECT * FROM author where isbn = (?)";
            PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
            preparedStatement.setInt(1, isbn);
            preparedStatement.execute();
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()){
                return null;
            }
            authors.add(result.getString(2));
            while (result.next()) {
                authors.add(result.getString(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return authors;
    }

    public static void main(String[] args) {
//        Author shehab = new Author(2, "shosh el nika2");
//        addAuthor(shehab);
//          Author shehab = getAuthor("shosh el nika3");
        List<String> list = getAuthorList(2);
        System.out.println(list);

        System.out.println("hi");
    }

}
