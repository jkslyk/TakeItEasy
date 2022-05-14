package com.example.takeiteasy.model;

public enum Permission {
    TICKETS_READ("tickets:read"),
    TICKETS_WRITE("tickets:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

}
