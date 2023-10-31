package org.example;

public class User {
    private String login;
    private String password;
    private String username;
    private boolean isAdmin;
    private String role;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
        role = "user";
    }

    public User(String login, String password, String username, boolean admin) {
        this.login = login;
        this.password = password;
        this.username = username;
        role = "admin";
        isAdmin = admin;
    }
}
