package org.example;

import java.util.List;

public interface AuthenticationProvider {
    String getUsernameByLoginAndPassword(String login, String password);

    boolean isAdmin(String username);

    boolean register(String login, String password, String username, String isAdmin);

    List<User> getUsers();
}