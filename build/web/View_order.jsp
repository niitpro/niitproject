<%-- 
    Document   : View_order
    Created on : Oct 28, 2019, 6:37:14 PM
    Author     : Chu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.models.Order"%>
<%@page import="dao.OrderDao"%>
<%@page import="dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Order View</title>
    </head>
    <body>
        <%! OrderDao v1 = new OrderDao();
            List<Order> ds=new ArrayList<>();
        %>
        <% ds = v1.getOrder(); %>
        <h1> View Order</h1>
        <% for (Order vv : ds){ %>
            <h2>Order_id:<span name="id"> <%=vv.getOrder_id()%></</h2>
            <h2> User_name: <%=vv.getUser_name()%></h2>
            <h2>Total money : <%=vv.getTotal_money()%></h2>
            <a href="View_order_detail.jsp?id=<%=vv.getOrder_id()%>"> Xem chi tiet </a>
        <% } %>
        
    </body>
</html>
