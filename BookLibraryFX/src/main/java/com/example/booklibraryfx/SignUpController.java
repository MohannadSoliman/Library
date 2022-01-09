package com.example.booklibraryfx;

import Backend.DTOs.UserDTO;
import Backend.Entities.User;
import Backend.SQLCommands.ConnectorSQL;
import Backend.SQLCommands.UserSQL;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Hyperlink alreadyExist;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private Label errorMessage;
    @FXML
    private Label successMessage;
    private boolean isPasswordValid(String password){
        return password.length() >= 8;
    }
    public void switchToLogin() throws IOException {
        Settings.stage.setScene(Settings.loginScene);
        Settings.stage.setTitle("Login");
        Settings.stage.show();
    }
    public void signUp(){
        ConnectorSQL csql = new ConnectorSQL();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        if(username.length() * firstName.length() * lastName.length() *
            email.length() * address.length() * phone.length() == 0){
            successMessage.setText("");
            errorMessage.setText("Please Fill All Empty Boxes!");
        }
        else if(!isPasswordValid(password)){
            successMessage.setText("");
            errorMessage.setText("Password Must be More Than 8 Characters");
        }else{

            UserDTO userDTO = new UserDTO();
            userDTO.username = username;userDTO.address = address; userDTO.email = email;
            userDTO.firstName = firstName;userDTO.lastName = lastName;userDTO.password = password;
            userDTO.role = "Customer";userDTO.phone = String.valueOf(phone);
            if(UserSQL.signup(userDTO)){
                errorMessage.setText("");
                successMessage.setText("Account Has Been Successfully Created!");
                usernameField.setText("");
                passwordField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                addressField.setText("");
                phoneField.setText("");
            }else{
                successMessage.setText("");
                errorMessage.setText("Username Already Exists!");

            }
        }


    }

}
