<%-- 
    Document   : View_ order_detail
    Created on : Oct 28, 2019, 7:33:55 PM
    Author     : Chu
--%>

<%@page import="com.models.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.Order_detail_dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View order detail</title>
        <style>
            th, td {
                border: 1px blue solid;
            }
        </style>
    </head>
    <body>
        <%! Order_detail_dao v1 = new Order_detail_dao();
            List<OrderDetail> ds =new ArrayList<>();
            
        %>
        <%  String id =(String) request.getParameter("id");
            int order_id = Integer.parseInt(id);
            System.out.println("!!!!!!!!"+order_id);;
            ds = v1.getOrderDetail(order_id); %>
        <h1> View  List Book</h1>
        <% System.out.println("FFFFFFF"+order_id); %>
        <table style="border: 1px solid aqua">
            <tr>
                <th>  book title</th>
                <th> book price </th>
                <th> quantity </th>
            </tr>  
            <% for (OrderDetail vv : ds) {%>
            <tr>
                <td> <%=vv.getBook_name()%></td>
                <td> <%=vv.getPrice()%></td>
                <td> <%=vv.getQuantity()%></td>
            </tr>
            <% } %>
        </table>
            <a href="View_order.jsp"> Quay lai </a>
    </body>
</html>
