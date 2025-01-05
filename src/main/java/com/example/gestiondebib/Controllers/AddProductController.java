package com.example.gestiondebib.Controllers;

import com.example.gestiondebib.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.gestiondebib.dao.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        if (nom == null || nom.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            throw new ServletException("All fields are required.");
        }

        Product product = new Product(nom, description);
        try {
            new ProductDAO().addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        response.sendRedirect("listProduct");
    }

}
