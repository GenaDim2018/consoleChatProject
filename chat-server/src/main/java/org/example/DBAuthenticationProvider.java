package org.example;

import java.sql.SQLException;
import java.util.List;

public class DBAuthenticationProvider implements AuthenticationProvider{
    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        try (DBConnection connection = new DBConnection()) {
            return connection.getUsername(login, password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isAdmin(String username) {
        try (DBConnection connection = new DBConnection()) {
            return connection.checkIsAdmin(username);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean register(String login, String password, String username, String isAdmin) {
        try (DBConnection connection = new DBConnection()) {
            if(isAdmin.equals("true")) return connection.registerUser(login, password, username, true);
            return connection.registerUser(login, password, username, false);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getUsers() {
        try (DBConnection connection = new DBConnection()) {
            return connection.getUserList();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
