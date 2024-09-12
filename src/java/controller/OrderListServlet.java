package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Order;

@WebServlet("/order-list")
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Fetch the list of orders from the database
        DBConnection dbConnection = new DBConnection();
        List<Order> orders = null;
        try {
            orders = dbConnection.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving orders", e);
        }

        // Set the orders attribute and forward to the JSP page
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/orderList.jsp").forward(request, response);
    }
}
