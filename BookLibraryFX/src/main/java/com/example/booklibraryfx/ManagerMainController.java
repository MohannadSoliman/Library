package com.example.booklibraryfx;

import java.io.IOException;

public class ManagerMainController {
    public void switchToEditProfile() throws IOException {
        Settings.stage.setScene(Settings.editUser);
        Settings.stage.setTitle("Edit Profile");
        Settings.stage.show();
    }
}
