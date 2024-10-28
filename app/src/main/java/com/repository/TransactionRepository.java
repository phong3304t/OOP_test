package com.repository;

import com.util.DatabaseConnection;
import com.model.transactions.BorrowTransaction;
import com.model.transactions.ReturnTransaction;
import com.model.exceptions.InsufficientQuantityException;

import java.sql.*;

public class TransactionRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * #7. Borrow document transaction
     */
    public static void borrowDocument(BorrowTransaction transaction) throws InsufficientQuantityException {
        String sql = "INSERT INTO Transactions(documentId, userId, type, borrowDate, dueDate) " +
                "VALUES(?, ?, ?, ?, ?)";
        String checkDocumentSql = "SELECT quantity FROM Documents WHERE id = ? AND quantity > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity - 1 WHERE id = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments + 1 WHERE id = ?";

        try {
            connection.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement pstmtCheck = connection.prepareStatement(checkDocumentSql);
                 PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
                 PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmtCheck.setInt(1, transaction.getDocumentId());
                ResultSet rs = pstmtCheck.executeQuery();

                if (rs.next()) {
                    pstmt.setInt(1, transaction.getDocumentId());
                    pstmt.setInt(2, transaction.getUserId());
                    pstmt.setString(3, transaction.getType());
                    pstmt.setDate(4, Date.valueOf(transaction.getBorrowDate()));
                    pstmt.setDate(5, Date.valueOf(transaction.getDueDate()));
                    pstmt.executeUpdate();

                    pstmtDocument.setInt(1, transaction.getDocumentId());
                    pstmtDocument.executeUpdate();

                    pstmtUser.setInt(1, transaction.getUserId());
                    pstmtUser.executeUpdate();

                    connection.commit();
                    System.out.println("Document borrowed successfully.");
                } else {
                    throw new InsufficientQuantityException("Document is not available for borrowing.");
                }
                rs.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Đặt lại chế độ auto-commit
            } catch (SQLException e) {
                System.out.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }

    /**
     * #8. Return document transaction
     */
    public static void returnDocument(ReturnTransaction transaction) {
        String sql = "INSERT INTO Transactions(documentId, userId, type, borrowDate, returnDate, fine) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        String checkUsersSql = "SELECT borrowedDocuments FROM Users WHERE id = ? AND borrowedDocuments > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity + 1 WHERE id = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments - 1 WHERE id = ?";

        try {
            connection.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement pstmtCheck = connection.prepareStatement(checkUsersSql);
                 PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
                 PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmtCheck.setInt(1, transaction.getUserId());
                ResultSet rs = pstmtCheck.executeQuery();

                if (rs.next()) {
                    pstmt.setInt(1, transaction.getDocumentId());
                    pstmt.setInt(2, transaction.getUserId());
                    pstmt.setString(3, transaction.getType());
                    pstmt.setDate(4, Date.valueOf(transaction.getBorrowDate()));
                    pstmt.setDate(5, Date.valueOf(transaction.getReturnDate()));
                    pstmt.setDouble(6, transaction.getFine());
                    pstmt.executeUpdate();

                    pstmtDocument.setInt(1, transaction.getDocumentId());
                    pstmtDocument.executeUpdate();

                    pstmtUser.setInt(1, transaction.getUserId());
                    pstmtUser.executeUpdate();

                    connection.commit();
                    System.out.println("Document returned successfully.");
                } else {
                    System.out.println("User has no borrowed documents to return.");
                }
                rs.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Đặt lại chế độ auto-commit
            } catch (SQLException e) {
                System.out.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }
}
