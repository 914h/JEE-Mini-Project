package com.example.gestiondebib.Controllers;

import com.example.gestiondebib.dao.ProductDAO;
import com.example.gestiondebib.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateProduct")

public class UpdateProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        Product product = new Product(id, nom, description);
        ProductDAO productDAO = new ProductDAO();

        try {
            productDAO.updateProduct(product);
            response.sendRedirect("listProduct");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating product.");
            request.getRequestDispatcher("/UpdateProduct.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();

        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/UpdateProduct.jsp").forward(request, response);
            } else {
                response.sendRedirect("listProduct");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("listProduct");
        }
    }
}