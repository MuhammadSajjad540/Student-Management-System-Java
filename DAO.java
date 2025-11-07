package dao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DAO {
    private final String url = "jdbc:mysql://localhost:3306/web?useSSL=false&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "root";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Updated driver
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    public List<Map<String, String>> getAllStudents() throws SQLException {
        List<Map<String, String>> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, String> student = new HashMap<>();
                student.put("id", rs.getString("id"));
                student.put("name", rs.getString("name"));
                student.put("email", rs.getString("email"));
                student.put("course", rs.getString("course"));
                student.put("country", rs.getString("country"));
                students.add(student);
            }
        }
        return students;
    }

    public int insertStudent(String name, String email, String course, String country) throws SQLException {
        String sql = "INSERT INTO students (name, email, course, country) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, course);
            stmt.setString(4, country);
            
            return stmt.executeUpdate();
        }
    }

    public int updateStudent(String name, String email, String course, String country) throws SQLException {
        String sql = "UPDATE students SET email=?, course=?, country=? WHERE name=?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, course);
            stmt.setString(3, country);
            stmt.setString(4, name);
            
            return stmt.executeUpdate();
        }
    }

    public int deleteStudent(String name) throws SQLException {
        String sql = "DELETE FROM students WHERE name=?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            return stmt.executeUpdate();
        }
    }
}