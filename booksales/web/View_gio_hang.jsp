<%-- 
    Document   : View_gio_hang
    Created on : Oct 29, 2019, 9:42:49 PM
    Author     : Chu
--%>

<%@page import="dao.Order_detail_dao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.models.GioHangList"%>
<%@page import="dao.OrderDao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View gio hang</title>
        <link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
        <script type="text/javascript" src="./js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-ui.js"></script>
        <script type="text/javascript" src="./js/view.js"></script>
        <link rel="stylesheet" type="text/css" href="./css/view.css">
    </head>
    <body>
         <%! OrderDao v1 = new OrderDao();
            Order_detail_dao v2=new Order_detail_dao();
            List<GioHangList> ds=new ArrayList<>();
        %>
        <% List< Object> lBook=(List<Object>) request.getAttribute("listBook"); 
        ds=v1.getListBookTitle(lBook);
        int soLu=0;
        %>
        <h1> Gio hang </h1>
        <script>
            var bb=0;
        </script>
        <table style="border: 1px solid aqua">
            <tr><th> ID Sach </th>
                <th> Ten sach </th>
                <th> Gia tien </th>
                <th> So luong </th>
                <th> Thanh tien </th>
            </tr>
            <% for (GioHangList vv: ds){ %>
            <tr>
                <td><%=vv.getId() %></td>
                <td><%=vv.getTen() %></td>
                <td> <%=vv.getPrice() %> <input type="hidden" value="<%= vv.getPrice()%>" name="price" id="price<%=vv.getTen() %>" /> </td> 
                <td> 1</td>
                <td class="tien"><%=vv.getPrice()%> </td>
            <script>
                var soLu=0;
                $("#soLuong<%=vv.getTen() %>").blur(funtion(){
                    var x=$("#number<%=vv.getTen() %>").val();
                    $("#tong").html(bb+x);
                });            
            </script>
        </table>
            <h1>Tong tien:</h1> <div id="tong"></div>
            <br/>
            <br/>
            <h2><a href="OrderDao?" >Thanh toan</a> </h2>
    </body>
</html>
