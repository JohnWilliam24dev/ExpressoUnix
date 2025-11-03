package com.johnwilliam.ExpressoUnix.Configs.Authentication.Enums;

public enum UserRole {
    ADMIN("ADMIN"),
    GESTAO("GESTAO"),
    MOTORISTA("MOTORISTA"),
    CLIENTE("CLIENTE");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String roleToString() {
        return role;
    }
}
