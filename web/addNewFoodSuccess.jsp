<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            max-width: 500px;
            width: 100%;
        }

        h1 {
            color: #4CAF50;
            font-size: 32px;
            margin-bottom: 20px;
            font-weight: 600;
        }

        p {
            font-size: 18px;
            color: #333;
            margin: 10px 0;
        }

        .order-info {
            background-color: #f9f9f9;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            text-align: left;
        }

        a.button-link {
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }

        button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 40px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        button:hover {
            background-color: #45a049;
            transform: translateY(-2px);
        }

        button:active {
            background-color: #3e8e41;
            transform: translateY(0);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Added Successfully!</h1>
        <div class="order-info">
            <%
                Order order = (Order) session.getAttribute("order");
                if (order != null) {
            %>
            <p><strong>Menu:</strong> <%= order.getMenu() %></p>
            <p><strong>Sweetness:</strong> <%= order.getSweetness() %></p>
            <p><strong>Temperature:</strong> <%= order.getTemperature() %></p>
            <p><strong>Topping:</strong> <%= order.getTopping() %></p>
            <%
                } else {
            %>
            <p>No order found in session.</p>
            <%
                }
            %>
        </div>
        <a href="addNewCha.html" class="button-link">
            <button>Add Another Order</button>
        </a>
    </div>
</body>
</html>
