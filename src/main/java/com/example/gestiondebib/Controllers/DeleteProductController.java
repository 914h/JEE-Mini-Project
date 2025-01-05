package com.example.gestiondebib.Controllers;

import com.example.gestiondebib.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteProduct")

public class DeleteProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            new ProductDAO().deleteProduct(id);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("listProduct");
}
}
