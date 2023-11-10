package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConnection implements AutoCloseable {
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private Properties props = new Properties();
    private Connection connection;
    private String tableName = "users";

    public DBConnection() throws SQLException, ClassNotFoundException {
        props.setProperty("user", "postgres");
        props.setProperty("password", "3654");
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, props);
    }

    public List<User> getUserList() throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT login as login, password as password, username as username FROM " + tableName + ";");
        while (rs.next()) {
            users.add(new User(rs.getString("login"), rs.getString("password"), rs.getString("username")));
        }
        rs.close();
        statement.close();
        return users;
    }

    public boolean checkUserExist(String username) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT username as username FROM " + tableName + " WHERE username = '" + username + "';");
        if (rs.next()) {
            rs.close();
            statement.close();
            return true;
        }
        rs.close();
        statement.close();
        return false;
    }

    public boolean checkIsAdmin(String username) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT username as username, isadmin as isAdmin FROM " + tableName + " WHERE username = '" + username + "';");
        if (rs.next() && rs.getBoolean("isAdmin")) {
            rs.close();
            statement.close();
            return true;
        }
        rs.close();
        statement.close();
        return false;

    }

    public boolean registerUser(String login, String password, String username, boolean isAdmin) throws SQLException {
        if (checkUserExist(username)) return false;
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO users (login, password, username, isadmin) VALUES ('" + login + "','" + password + "','" + username + "'," + isAdmin + ");");
        statement.close();
        return true;
    }
    public String getUsername(String login, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT login as login, username as username FROM " + tableName + " WHERE login = '" + login + "';");
        if (rs.next()) {
            String username = rs.getString("login");
            rs.close();
            statement.close();
            return username;
        }
        return "";
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
