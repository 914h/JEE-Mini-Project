package com.example.gestiondebib.Controllers;
import com.example.gestiondebib.model.Product;
import com.example.gestiondebib.util.DatabaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listProduct")

public class ListProductController extends HttpServlet {

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `product`");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setNom(rs.getString("nom"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }
        } catch (SQLException e){
            System.err.println("Database query error: " + e.getMessage());
            e.printStackTrace();
        }
        if (products.isEmpty()) {
            System.err.println("No products found in the database!");
        }
        return products;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/ListProduct.jsp").forward(request, response);
        System.out.println(products);

    }
}
