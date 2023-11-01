package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {
    private final List<User> users;

    public InMemoryAuthenticationProvider() {
        this.users = new ArrayList<>();
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (Objects.equals(user.getPassword(), password) && Objects.equals(user.getLogin(), login)) {
                return user.getUsername();
            }
        }
        return null;
    }


    //    public synchronized boolean register(String login, String password, String username) {
//        for (User user : users) {
//            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getLogin(), login)) {
//                return false;
//            }
//        }
//        users.add(new User(login, password, username));
//        return true;
//    }
    @Override
    public synchronized boolean register(String login, String password, String username, String isAdmin) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getLogin(), login)) {
                return false;
            }
        }
        if (isAdmin.equals(("true"))) {
            users.add(new User(login, password, username, true));
        } else users.add(new User(login, password, username, false));
        return true;
    }

    @Override
    public synchronized boolean isAdmin(String username) {
        for (User user : users) {
            if (user.isAdmin() && user.getUsername().equals(username)) return true;
        }
        return false;
    }
}
