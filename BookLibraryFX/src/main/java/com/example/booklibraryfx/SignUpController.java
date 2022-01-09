package com.example.booklibraryfx;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Hyperlink alreadyExist;
    public void switchToLogin() throws IOException {
        Settings.stage.setScene(Settings.loginScene);
        Settings.stage.setTitle("Login");
        Settings.stage.show();
    }

}
