/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chu
 */
public class GioHang extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object bookId=request.getParameter("idbook");
        HttpSession ss=request.getSession();
        List< Object> lBook=(List<Object>) ss.getAttribute("listBook");
        if (lBook == null){
            lBook =new ArrayList<>();
            if (bookId != null){
                lBook.add(bookId);
                ss.setAttribute("listBook", lBook);
                
            }
        }else{
            if (bookId != null){
                lBook.add(bookId);
                ss.setAttribute("listBook", lBook);
            }
        }
        
        
    }
}
