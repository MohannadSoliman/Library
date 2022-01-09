import DTOs.BookDTO;
import DTOs.UserDTO;
import Entities.User;
import SQLCommands.BookSQL;
import SQLCommands.ConnectorSQL;
import SQLCommands.UserSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // initiate connection
        new ConnectorSQL();
        /*String queryString = "SELECT * FROM users WHERE username=(?) and user_password=(?)";
        try {
            PreparedStatement preparedStmt = ConnectorSQL.connection.prepareStatement(queryString);
            preparedStmt.setString(1, "The LOL username");
            preparedStmt.setString(2, "The LOL password");
            ResultSet result = preparedStmt.executeQuery();

            if (result.next()) {
                // get third column which is first name
                System.out.println(result.getString(3));
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/
//        User user = UserSQL.login("The LOL username", "The LOL password");
//        System.out.println(user.getRole());
//        UserDTO userDTO = new UserDTO();
//        userDTO.username = "The LOL new";
//        userDTO.password = "The LOL new pass";
//        userDTO.firstName = "The LOL new first";
//        userDTO.lastName = "The LOL new last";
//        userDTO.address = "The LOL new address";
//        userDTO.email = "The LOL new email";
//        userDTO.phone = "The LOL new phone";
//        System.out.println(UserSQL.signup(userDTO));
//        System.out.println(UserSQL.promote("The LOL new"));
//        System.out.println(UserSQL.update("The LOL new", "new address update", "address"));
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.isbn = 123;
//        bookDTO.title = "test title";
//        bookDTO.quantity = 12;
//        bookDTO.threshold = 5;
//        bookDTO.publisher = "The LOL publisher";
//        bookDTO.publicationYear = "1234-01-21";
//        bookDTO.price = 21;
//        bookDTO.category = "Religion";
//        bookDTO.authors = null;
//        BookSQL.addBook(bookDTO);
//        System.out.println(BookSQL.getBook("The LOL publisher", "publisher").get(1).getQuantity());
//        System.out.println(BookSQL.getAllBooks().get(2).getTitle());
    }

}
