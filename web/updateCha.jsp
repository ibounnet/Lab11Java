<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }
        .form-group select,
        .form-group input[type="radio"] {
            width: calc(100% - 20px);
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            color: #333;
        }
        .form-group input[type="radio"] {
            width: auto;
        }
        .form-group label.radio-label {
            margin-right: 10px;
            font-weight: normal;
            color: #333;
        }
        .form-group button {
            width: 100%;
            padding: 10px 15px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .form-group button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Order</h1>

        <%
            Order order = (Order) request.getAttribute("order");
            if (order == null) {
                out.println("<p>No order found.</p>");
            } else { 
        %>
        <form action="updateOrder" method="post">
            <!-- Hidden field to pass the order ID -->
            <input type="hidden" name="orderId" value="<%= order.getId() %>">
            
            <div class="form-group">
                <label for="menu">Menu:</label>
                <select id="menu" name="menu" required>
                    <% String[] menu = {"Coffee", "Espresso", "Latte"}; %>
                    <% for (String item : menu) { %>
                        <option value="<%= item %>" <%= item.equals(order.getMenu()) ? "selected" : "" %>><%= item %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label>Sweetness:</label><br>
                <% String[] sweet = {"0%", "25%", "50%", "75%", "100%"}; %>
                <% for (String level : sweet) { %>
                    <input type="radio" id="sweet<%= level.replace("%", "") %>" name="sweetness" value="<%= level %>" <%= level.equals(order.getSweetness()) ? "checked" : "" %>>
                    <label for="sweet<%= level.replace("%", "") %>" class="radio-label"><%= level %></label>
                <% } %>
            </div>
            <div class="form-group">
                <label>Temperature:</label><br>
                <% String[] temperature = {"cold", "hot"}; %>
                <% for (String temp : temperature) { %>
                    <input type="radio" id="temp<%= temp %>" name="temperature" value="<%= temp %>" <%= temp.equals(order.getTemperature()) ? "checked" : "" %>>
                    <label for="temp<%= temp %>" class="radio-label"><%= temp %></label>
                <% } %>
            </div>
            <div class="form-group">
                <label for="topping">Topping:</label>
                <select id="topping" name="topping">
                    <% String[] topping = {"None", "Tapioca", "Jelly", "Pudding", "Whipped Cream", "Chocolate Syrup"}; %>
                    <% for (String top : topping) { %>
                        <option value="<%= top %>" <%= top.equals(order.getTopping()) ? "selected" : "" %>><%= top %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">Update Order</button>
            </div>
        </form>
        <% } %>
    </div>
</body>
</html>
