<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 3px;
            color: #fff;
        }
        .btn-edit {
            background-color: #007bff;
        }
        .btn-delete {
            background-color: #dc3545;
        }
    </style>
</head>
<body>
    <h1>Order List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Menu</th>
                <th>Sweetness</th>
                <th>Temperature</th>
                <th>Topping</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                if (orders != null) {
                    for (Order order : orders) {
            %>
            <tr>
                <td><%= order.getId() %></td>
                <td><%= order.getMenu() %></td>
                <td><%= order.getSweetness() %></td>
                <td><%= order.getTemperature() %></td>
                <td><%= order.getTopping() %></td>
                <td>
                    <a href="updateOrder?orderId=<%= order.getId() %>" class="btn btn-edit">Edit</a>
                    <a href="deleteOrder?orderId=<%= order.getId() %>" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this order?');">Delete</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No orders found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
