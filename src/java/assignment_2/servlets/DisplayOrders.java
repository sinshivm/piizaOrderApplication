/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2.servlets;

import assignment_2.PizzaOrder;
import assignment_2.db.OrderDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Misha
 */
public class DisplayOrders extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        
        PizzaOrder pizzaOrder = (PizzaOrder)session.getAttribute("pizzaOrder");
        PrintWriter out = response.getWriter();
        
        OrderDB orderDB = new OrderDB();
        String name = null;
        String phone = null;
        String pizzaSize = null;
        String toppings = null;
        boolean delivery = false;
        double price = 0;
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>"); 
            out.println("<h1>This is your order </h1>");
            ArrayList<PizzaOrder> orders = orderDB.displayOrder(pizzaOrder);
            out.println("<h1>This is your order </h1>");
            for(int i = 0 ; i<orders.size();i++){
                name = orders.get(i).getName();
                phone = orders.get(i).getPhone();
                pizzaSize = orders.get(i).getPizzaSize();
                toppings = orders.get(i).getToppings();
                delivery = orders.get(i).isDelivery();
                price = orders.get(i).getPrice();
                
                out.println("<p>"+ name +" " + phone + " "+pizzaSize +" " +" "+toppings+" " + delivery+" " + price+"</p>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
           System.out.println(ex);
        }
    }
}
