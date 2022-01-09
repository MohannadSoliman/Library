package Entities;

import DTOs.TopUserDTO;

public class TopUser {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phone;
    private String role;
    private int quantityPurchased;

    public TopUser(TopUserDTO topUserDTO) {
        this.username = topUserDTO.username;
        this.firstName = topUserDTO.firstName;
        this.lastName = topUserDTO.lastName;
        this.address = topUserDTO.address;
        this.password = topUserDTO.password;
        this.email = topUserDTO.email;
        this.phone = topUserDTO.phone;
        this.role = topUserDTO.role;
        this.quantityPurchased = topUserDTO.quantityPurchased;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() { return role; }

    public int getQuantityPurchased() { return quantityPurchased; }
}
