package com.example.gestiondebib.dao;

import com.example.gestiondebib.model.Product;
import com.example.gestiondebib.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (nom, description) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getNom());
            stmt.setString(2, product.getDescription());
            stmt.executeUpdate();
        }
    }
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET nom = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getNom());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getId());
            stmt.executeUpdate();
        }
    }
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
                }
            }
        }
        return null;
    }

}

