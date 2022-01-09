package SQLCommands;

import Entities.CartItem;
import Entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseSQL {

    public static boolean checkout(User user){
        ConnectorSQL connection = new ConnectorSQL();
        ArrayList<CartItem> cart = user.getCart();
        String username = user.getUsername();
        for(int i = 0 ; i < cart.size(); i++) {
            try {
                int isbn = cart.get(i).getIsbn();
                int quantity = cart.get(i).getQuantity();
                String query = "call buy_book("+isbn+", \""+username+"\", "+quantity+");";
                PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws SQLException {
        ConnectorSQL connection = new ConnectorSQL();
        String username = "The LOL username3";
            int isbn = 2;
            int quantity = 14;

            try {
                String query = "call buy_book("+isbn+", \""+username+"\", "+quantity+");";
                PreparedStatement preparedStatement = ConnectorSQL.connection.prepareStatement(query);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("failed");
            }
        }
    }


