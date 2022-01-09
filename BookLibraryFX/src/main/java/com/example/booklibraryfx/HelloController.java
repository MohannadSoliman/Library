package com.example.booklibraryfx;

import Backend.Entities.Manager;
import Backend.Entities.User;
import Backend.SQLCommands.ConnectorSQL;
import Backend.SQLCommands.UserSQL;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class HelloController {




    //                                  Login
    @FXML
    private Hyperlink newUser;
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label passwordError;

    private boolean isPasswordValid(String password){
        if(password.length() >= 8) return true;
        return false;
    }
    public void login(){
        ConnectorSQL csql = new ConnectorSQL();
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        if(!isPasswordValid(password)){
            passwordError.setText("Password must be more than 8 characters!");
        }else if(username.length() * password.length() == 0){
            passwordError.setText("Please Fill All Empty Fields!");
        }else{
            User user = UserSQL.login(username, password);
            if(user == null){
                passwordError.setText("Username or Password are Wrong!");
            }else{
                if(user.getRole().equalsIgnoreCase("Customer")){
                    Settings.user = user;
                    Settings.manager = null;
                    try {
                        switchToMainCustomer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Settings.manager = (Manager) user;
                    Settings.user = null;
                    try {
                        switchToMainManager();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    public void switchToSignUp() throws IOException {
        Settings.stage.setScene(Settings.signUpScene);
        Settings.stage.setTitle("Sign Up");
        Settings.stage.show();
    }
    public void switchToMainCustomer() throws IOException {
        Settings.stage.setScene(Settings.customerScene);
        Settings.stage.setTitle("Customer");
        Settings.stage.show();
    }
    public void switchToMainManager() throws  IOException{
        Settings.stage.setScene(Settings.managerScene);
        Settings.stage.setTitle("Customer");
        Settings.stage.show();
    }


//                              Sign up
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}