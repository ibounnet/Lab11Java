package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        // Delete the order from the database
        DBConnection dbConnection = new DBConnection();
        try {
            if (orderId != null && dbConnection.deleteOrder(orderId)) {
                response.sendRedirect("order-list");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete order.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error deleting order", e);
        }
    }
}
