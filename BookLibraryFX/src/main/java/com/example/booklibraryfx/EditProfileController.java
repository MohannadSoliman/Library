package com.example.booklibraryfx;

import java.io.IOException;

public class EditProfileController {
    public void switchToMainCustomer() throws IOException {
        if(Settings.user == null){
            Settings.stage.setScene(Settings.managerScene);
            Settings.stage.setTitle("Manager");
            Settings.stage.show();
        }else{
            Settings.stage.setScene(Settings.customerScene);
            Settings.stage.setTitle("Customer");
            Settings.stage.show();
        }

    }
}
