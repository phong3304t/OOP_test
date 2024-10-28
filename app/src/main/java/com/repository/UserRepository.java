package com.repository;

import com.model.users.*;
import com.model.exceptions.UserNotFoundException;
import com.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * #6.
     * @param user User
     */
    public static void addUser(User user) {
        String sql = "INSERT INTO " +
                "Users(position, name, email, password, borrowedDocuments) " +
                "VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getPosition());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getBorrowedDocuments());
            pstmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User findUserByEmail(String user_email)
            throws SQLException, UserNotFoundException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user_email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = 0;
                return new User(name, email, password, borrowedDocuments);
            }
            rs.close();
        }
        throw new UserNotFoundException("User Not Found");
    }

    /**
     * #9.
     * @return List
     */
    public static List<User> getUser() throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int borrowedDocuments = rs.getInt("borrowedDocuments");
                User user = new User(name, email, password, borrowedDocuments);
                results.add(user);
            }
        }
        return results;
    }
}