package org.r4ppz.model;

public class DefaultUser {
    private final String username = "r4ppz";
    private final String password = "12312005";

    private static DefaultUser defaultUser;

    private DefaultUser() {

    }

    public static DefaultUser getInstanc() {
        if (defaultUser == null) {
            defaultUser = new DefaultUser();
        }

        return defaultUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
