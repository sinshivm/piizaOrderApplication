/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2.servlets;

import assignment_2.PizzaOrder;
import assignment_2.db.OrderDB;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Misha
 */
public class BeginOrder extends HttpServlet {
    
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException{ 
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        
        PizzaOrder pizzaOrder = new PizzaOrder(name,phone); 
        HttpSession session = request.getSession();
        
        session.setAttribute("pizzaOrder", pizzaOrder);
        
        response.sendRedirect("PlaceOrder.do");
    }
    
    
}
