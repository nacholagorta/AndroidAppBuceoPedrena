package com.example.pccom.androidappbuceopedrena;

public final class UserSingletonID {

    private static UserSingletonID INSTANCE;
    private String info = "Initial info class";

    private UserSingletonID() {
    }

    public static UserSingletonID getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserSingletonID();
        }

        return INSTANCE;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
// getters and setters
}
