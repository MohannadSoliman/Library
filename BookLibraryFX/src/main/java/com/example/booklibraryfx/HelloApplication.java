package com.example.booklibraryfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Settings.stage = stage;
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        FXMLLoader fxmlLoaderSignUp = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Settings.loginScene = new Scene(fxmlLoaderLogin.load());
        Settings.signUpScene = new Scene(fxmlLoaderSignUp.load());
        stage.setTitle("Book Library");
        stage.setScene(Settings.loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}