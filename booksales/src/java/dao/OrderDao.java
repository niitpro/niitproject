/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.models.GioHangList;
import com.models.Order;
import com.models.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chu
 */
public class OrderDao extends HttpServlet {

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

    public List<Order> getOrder() {
        List<Order> ds=null;
        ds = new ArrayList<>();
        Connection con = getConnection();
        try {
            //System.out.println("AAAAAAAAAAAA");
            String viewList = "Select orders.order_id, user.name, orders.total_money "
                    + "from orders, user "
                    + "where user.id=orders.user_id";
            Statement stmt = con.createStatement();
            System.out.println(viewList);
            ResultSet rs = null;
            //System.out.println("BBBBBBBB");
            rs = stmt.executeQuery(viewList);
            //System.out.println("DDDDDDD");
            while (rs.next()){
                Order hh = null;
                hh = new Order(rs.getInt("order_id"),rs.getInt("total_money"),rs.getString("name"));
                ds.add(hh);
            }
        } catch (SQLException x) {
        }
        return ds;
    }
    
    public List < GioHangList > getListBookTitle( List idBook){
        List <GioHangList> ds=null;
        ds=new ArrayList<>();
        Connection con=getConnection();
        for ( Object i:idBook){
            try{
                String view="select id,title,price from book where id="+i;
                Statement stmt=con.createStatement();
                ResultSet rs=null;
                rs=stmt.executeQuery(view);
                while (rs.next()){
                    GioHangList kqua=new GioHangList(rs.getInt("id"),rs.getString("title"),rs.getInt("price"));
                    ds.add(kqua);
                }
            }catch(SQLException x){}
        }  
        
        return ds;
    }
        
    public static void addBook(int order_id, int total_money, int price) {
        Connection con = getConnection();
        try {
            String addBook_ = " INSERT INTO orders(order_id, total_money, price) VALUES (" +
                    order_id+" , "+total_money + " , "+price+ " );";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(addBook_);
            con.close();
        } catch (SQLException x) {
        }

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        

        RequestDispatcher rt = request.getRequestDispatcher("View_order.jsp");
        rt.forward(request, response);
    }

}
