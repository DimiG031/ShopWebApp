package com.example.models;

public enum UserType {
    ADMINISTRATOR("administrator"), USER("user"), BANNED("banned");
    private final String role;

    private UserType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
