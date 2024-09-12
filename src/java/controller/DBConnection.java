package controller;

import model.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private final String url = "jdbc:mysql://localhost:3306/chamai";  // Update to your database details
    private final String user = "root";                               // Update to your database username
    private final String password = "1234";                           // Update to your database password

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insertNewOrder(Order order) throws Exception {
        String insertQuery = "INSERT INTO orders (menu, sweetness, temperature, topping) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {

            pstmt.setString(1, order.getMenu());
            pstmt.setString(2, order.getSweetness());
            pstmt.setString(3, order.getTemperature());
            pstmt.setString(4, order.getTopping());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
public List<Order> getAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT * FROM orders";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(selectQuery);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getString("menu"),
                        rs.getString("sweetness"),
                        rs.getString("temperature"),
                        rs.getString("topping")
                );
                orders.add(order);
            }
        }
        return orders;
    }

    public Order getOrderById(String orderId) throws Exception {
        String selectQuery = "SELECT * FROM orders WHERE id = ?";
        Order order = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(selectQuery)) {

            pstmt.setString(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new Order(
                        rs.getString("id"),
                        rs.getString("menu"),
                        rs.getString("sweetness"),
                        rs.getString("temperature"),
                        rs.getString("topping")
                );
            }
        }

        return order;
    }

    public boolean updateOrder(Order order) throws Exception {
        String updateQuery = "UPDATE orders SET menu=?, sweetness=?, temperature=?, topping=? WHERE id=?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {

            pstmt.setString(1, order.getMenu());
            pstmt.setString(2, order.getSweetness());
            pstmt.setString(3, order.getTemperature());
            pstmt.setString(4, order.getTopping());
            pstmt.setString(5, order.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteOrder(String orderId) throws Exception {
    String deleteQuery = "DELETE FROM orders WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {

        pstmt.setString(1, orderId);

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    }
}

}
