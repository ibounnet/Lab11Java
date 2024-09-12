package controller;

import java.io.IOException;
import java.util.logging.Logger;
import model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import java.util.logging.Level;

@WebServlet("/addorder")
public class AddOrder extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddOrder.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String menu = request.getParameter("menu");
        String sweetness = request.getParameter("sweetness");
        String temperature = request.getParameter("temperature");
        String topping = request.getParameter("topping");

        // Validate form data
        if (isNullOrEmpty(menu) || isNullOrEmpty(sweetness) || isNullOrEmpty(temperature) || isNullOrEmpty(topping)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        // Create a new Order object
        Order order = new Order(menu, sweetness, temperature, topping);

        // Log the order creation
        logger.log(Level.INFO, "Order created: {0}", order);

        // Connect to database and insert order
        DBConnection dbConnection = new DBConnection();
        try {
            if (!dbConnection.insertNewOrder(order)) {
                logger.log(Level.SEVERE, "Database insert failed for order: {0}", order);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during database operation", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            return;
        }

        // Store order in session
        HttpSession session = request.getSession();
        session.setAttribute("order", order);

        // Forward to success JSP
        RequestDispatcher rd = request.getRequestDispatcher("/addNewFoodSuccess.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles order submission and summary display";
    }

    // Utility method to check if a string is null or empty
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
