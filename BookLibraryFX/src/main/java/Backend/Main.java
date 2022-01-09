package Backend;

import Backend.Entities.User;
import Backend.SQLCommands.ConnectorSQL;
import Backend.SQLCommands.UserSQL;

public class Main {
    public static void main(String[]args){
        ConnectorSQL csql = new ConnectorSQL();
        UserSQL.update("The LOL username", "newFirst", "first_name");

    }
}
