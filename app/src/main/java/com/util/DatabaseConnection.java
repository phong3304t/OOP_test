package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Connect constructor.
     */
    public static void connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connect successfully!");
            } catch (SQLException e) {
                handleError("Can not connected.", e);
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    /**
     * Create tables.
     */
    public static void createTables() {
        String sqlDocuments = "CREATE TABLE IF NOT EXISTS Documents (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "type ENUM('Book', 'Magazine', 'Thesis')," +
                "title VARCHAR(200) NOT NULL," +
                "author VARCHAR(50) NOT NULL," +
                "quantity INT NOT NULL," +
                "genre VARCHAR(20)," +
                "issueNumber INT," +
                "topic VARCHAR(50))";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "position ENUM('User')," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) UNIQUE NOT NULL," +
                "password VARCHAR(100) NOT NULL," +
                "borrowedDocuments INT DEFAULT 0)";
        String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions (" +
                "transactionId INT PRIMARY KEY AUTO_INCREMENT," +
                "documentId INT NOT NULL," +
                "userId INT NOT NULL," +
                "type ENUM('Borrow', 'Return')," +
                "borrowDate DATE DEFAULT CURRENT_DATE," +
                "dueDate DATE," +
                "returnDate DATE," +
                "fine DOUBLE," +
                "FOREIGN KEY (documentId) REFERENCES Documents(id)," +
                "FOREIGN KEY (userId) REFERENCES Users(id))";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(sqlDocuments);
            stmt.execute(sqlUsers);
            stmt.execute(sqlTransactions);
            System.out.println("Create tables successful!");
        } catch (SQLException e) {
            handleError("Cannot create tables.", e);
        }
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to MySQL database closed.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                connection = null;
            }
        }
    }

    // Hàm báo lỗi
    private static void handleError(String message, SQLException e) {
        System.out.println(message + " Error: " + e.getMessage());
    }
}
