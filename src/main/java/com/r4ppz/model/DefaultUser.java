package com.r4ppz.model;

public class DefaultUser {

    private static DefaultUser instance;

    private DefaultUser() {

    }

    public static DefaultUser getInstance() {
        if (instance == null) {
            instance = new DefaultUser();
        }

        return instance;
    }

    public String getUsername() {
        return "r4ppz";
    }

    public String getPassword() {
        return "12312005";
    }
}
