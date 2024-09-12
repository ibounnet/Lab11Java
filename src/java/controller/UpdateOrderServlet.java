package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Order;

@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {

    private final String url = "jdbc:mysql://localhost:3306/chamai";
    private final String user = "root";
    private final String password = "1234";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        // Fetch the order details from the database
        DBConnection dbConnection = new DBConnection();
        Order order = null;

        try {
            order = dbConnection.getOrderById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving order", e);
        }

        if (order != null) {
            request.setAttribute("order", order);
            request.getRequestDispatcher("/updateCha.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("orderId");
        String menu = request.getParameter("menu");
        String sweetness = request.getParameter("sweetness");
        String temperature = request.getParameter("temperature");
        String topping = request.getParameter("topping");

        // Update the order in the database
        Order order = new Order(id, menu, sweetness, temperature, topping);
        DBConnection dbConnection = new DBConnection();

        try {
            if (!dbConnection.updateOrder(order)) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update order.");
            } else {
                response.sendRedirect("order-list");  // Redirect to the order list page
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Database error occurred", e);
        }
    }
}
