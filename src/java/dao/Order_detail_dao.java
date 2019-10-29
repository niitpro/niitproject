/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.models.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chu
 */
public class Order_detail_dao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static Connection con = null;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/books", "root", "");
                return con;
            } catch (ClassNotFoundException | SQLException x) {
                System.out.println(x);
            }
        }
        return null;
    }

    public List<OrderDetail> getOrderDetail( int id) {
        List<OrderDetail> ds = null;
        ds = new ArrayList<>();
        Connection con = getConnection();
        try {

            String viewList = "Select order_detail.order_id, book.title, book.price, order_detail.quantity "
                    + "from order_detail, user, orders,book "
                    + "where order_detail.book_id=book.id and order_detail.order_id=orders.order_id and order_detail.price=book.price and order_detail.order_id="+id;
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(viewList);
            while (rs.next()) {
                OrderDetail hh = null;
                hh = new OrderDetail(rs.getInt("order_detail.order_id"), rs.getString("book.title"), rs.getInt("book.price"), rs.getInt("order_detail.quantity"));
                ds.add(hh);
            }

            con.close();
        } catch (SQLException x) {
        }
        return ds;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id =(String) request.getParameter("id");
        int order_id = Integer.parseInt(id);
        
        RequestDispatcher rt = request.getRequestDispatcher("View_order_detail.jsp?id="+order_id);
        request.setAttribute("id",order_id);
        rt.forward(request, response);
    }

}
