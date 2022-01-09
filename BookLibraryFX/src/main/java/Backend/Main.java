package Backend;

import Backend.Entities.Book;
import Backend.Entities.User;
import Backend.SQLCommands.BookSQL;
import Backend.SQLCommands.ConnectorSQL;
import Backend.SQLCommands.UserSQL;

public class Main {
    public static void main(String[]args){
        ConnectorSQL csql = new ConnectorSQL();
        //UserSQL.update("The LOL username", "newFirst", "first_name");
        //BookSQL.getBook("THE LOL2", "Title");
        System.out.println(BookSQL.getBook("THE LOL2", "Title").get(0).getIsbn());
    }
}
