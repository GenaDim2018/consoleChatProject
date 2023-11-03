package org.example;

import java.util.List;

public interface AuthenticationProvider {
    String getUsernameByLoginAndPassword(String login, String password);

    boolean isAdmin(String login);

    boolean register(String login, String password, String username, String isAdmin);

    List<User> getUsers(String tableName);
}