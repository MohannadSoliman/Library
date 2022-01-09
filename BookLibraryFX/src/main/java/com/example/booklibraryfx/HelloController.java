package com.example.booklibraryfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        if(!isPasswordValid(password)){
            passwordError.setText("Password must be more than 8 characters!");
        }
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }
    public void switchToSignUp() throws IOException {
        Settings.stage.setScene(Settings.signUpScene);
        Settings.stage.setTitle("Sign Up");
        Settings.stage.show();
    }

//                              Sign up
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}