package com.example.booklibraryfx;

import Backend.SQLCommands.ConnectorSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConnectorSQL connectorSQL = new ConnectorSQL();

        Settings.stage = stage;
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        FXMLLoader fxmlLoaderSignUp = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        FXMLLoader fxmlLoaderCustomer = new FXMLLoader(HelloApplication.class.getResource("UserMain.fxml"));
        FXMLLoader fxmlLoaderEditProfile = new FXMLLoader(HelloApplication.class.getResource("EditProfile.fxml"));
        FXMLLoader fxmlLoaderManager = new FXMLLoader(HelloApplication.class.getResource("ManagerMain.fxml"));
        String style = Objects.requireNonNull(HelloApplication.class.getResource("styles/main.css")).toExternalForm();

        Settings.customerScene = new Scene(fxmlLoaderCustomer.load());
        Settings.loginScene = new Scene(fxmlLoaderLogin.load());
        Settings.signUpScene = new Scene(fxmlLoaderSignUp.load());
        Settings.editUser = new Scene(fxmlLoaderEditProfile.load());
        Settings.managerScene = new Scene(fxmlLoaderManager.load());

        Settings.customerScene.getStylesheets().add(style);

        stage.setTitle("Login");
        stage.setScene(Settings.customerScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}