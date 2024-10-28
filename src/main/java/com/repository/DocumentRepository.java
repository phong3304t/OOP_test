package com.repository;

import com.model.documents.*;
import com.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * #1.
     * @param doc Document
     */
    public static void addDocument(Document doc) {
        String sql = "INSERT INTO " +
                "Documents(type, title, author, quantity, genre, issueNumber, topic)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, doc.getType());
            pstmt.setString(2, doc.getTitle());
            pstmt.setString(3, doc.getAuthor());
            pstmt.setInt(4, doc.getQuantity());
            switch (doc) {
                case Book book -> {
                    pstmt.setString(5, book.getGenre());
                    pstmt.setNull(6, Types.INTEGER);
                    pstmt.setNull(7, Types.VARCHAR);
                }
                case Magazine magazine -> {
                    pstmt.setNull(5, Types.VARCHAR);
                    pstmt.setInt(6, magazine.getIssueNumber());
                    pstmt.setNull(7, Types.VARCHAR);
                }
                case Thesis thesis -> {
                    pstmt.setNull(5, Types.VARCHAR);
                    pstmt.setNull(6, Types.INTEGER);
                    pstmt.setString(7, thesis.getTopic());
                }
                default -> {
                }
            }
            pstmt.executeUpdate();
            System.out.println("Document added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #2.
     * @param docId ID
     */
    public static void removeDocument(int docId) {
        String sql = "DELETE FROM Documents WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, docId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document removed successfully!");
            } else {
                System.out.println("Document not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #3.
     */
    public static void updateDocument(Document doc) {
        String sql = "UPDATE Documents SET type = ?, title = ?, " +
                "author = ?, quantity = ?, genre = ?, issueNumber = ?, topic = ? " +
                "WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, doc.getType());
            pstmt.setString(2, doc.getTitle());
            pstmt.setString(3, doc.getAuthor());
            pstmt.setInt(4, doc.getQuantity());
            switch (doc) {
                case Book book -> {
                    pstmt.setString(5, book.getGenre());
                    pstmt.setNull(6, Types.INTEGER);
                    pstmt.setNull(7, Types.VARCHAR);
                }
                case Magazine magazine -> {
                    pstmt.setNull(5, Types.VARCHAR);
                    pstmt.setInt(6, magazine.getIssueNumber());
                    pstmt.setNull(7, Types.VARCHAR);
                }
                case Thesis thesis -> {
                    pstmt.setNull(5, Types.VARCHAR);
                    pstmt.setNull(6, Types.INTEGER);
                    pstmt.setString(7, thesis.getTopic());
                }
                default -> {
                }
            }
            pstmt.setInt(8, doc.getId());
            pstmt.executeUpdate();
            System.out.println("Document updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #4.
     * @param keyword word to find out
     * @return List
     */
    public static List<Document> findDocument(String keyword) {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents " +
                "WHERE title LIKE ? OR author LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                Document doc = switch (type) {
                    case "Book" -> {
                        String genre = rs.getString("genre");
                        yield new Book(title, author, quantity, genre);
                    }
                    case "Magazine" -> {
                        int issueNumber = rs.getInt("issueNumber");
                        yield new Magazine(title, author, quantity, issueNumber);
                    }
                    case "Thesis" -> {
                        String topic = rs.getString("topic");
                        yield new Thesis(title, author, quantity, topic);
                    }
                    default -> null;
                };
                if (doc != null) {
                    results.add(doc);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /**
     * #5.
     */
    public static List<Document> getDocuments() {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                Document doc = switch (type) {
                    case "Book" -> {
                        String genre = rs.getString("genre");
                        yield new Book(title, author, quantity, genre);
                    }
                    case "Magazine" -> {
                        int issueNumber = rs.getInt("issueNumber");
                        yield new Magazine(title, author, quantity, issueNumber);
                    }
                    case "Thesis" -> {
                        String topic = rs.getString("topic");
                        yield new Thesis(title, author, quantity, topic);
                    }
                    default -> null;
                };
                if (doc != null) {
                    results.add(doc);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
