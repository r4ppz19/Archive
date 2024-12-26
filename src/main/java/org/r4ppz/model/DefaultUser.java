package org.r4ppz.model;

public class DefaultUser {
    private final String username = "r4ppz";
    private final String password = "12312005";

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
        return username;
    }

    public String getPassword() {
        return password;
    }
}
