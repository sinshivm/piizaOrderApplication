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
        // start session created in BeginOrder servlet
        HttpSession session = request.getSession();
        // get PizzaOrder class from session's attribute
        PizzaOrder pizzaOrder = (PizzaOrder)session.getAttribute("pizzaOrder");
        // get driver,connURL,database,user and password values from context-param
        String driver = getServletContext().getInitParameter("driver");
        String connURL = getServletContext().getInitParameter("connURL");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password  = getServletContext().getInitParameter("password");
        // create an instance of OrderDB class
        OrderDB orderDB = new OrderDB(driver , connURL, database, user, password);
        // initialize the parameters for output of OrderDb class method
        String name = null;
        String phone = null;
        String pizzaSize = null;
        String toppings = null;
        boolean delivery = false;
        double price = 0;
        // initialize a PrintWriter
        PrintWriter out = response.getWriter();
        try {
            // craete HTML document
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>"); 
            // print the parameter in table format
            out.println("<table>");
            // defualt row
            out.println("<tr><th>Name</th><th>Phone</th><th>Pizza Size</th>"
                    + "<th>Toppings</th><th>Delivery</th><th>Price</th></tr>");
            // for each element from orderDb class displayOrder method print it 
            // in the table format
            for(int i = 0 ; i<orderDB.displayOrder().size();i++){
                // assign elemnt of displayOrder method to the PizzaOrder class instance 
                // pizzaOrder
                pizzaOrder = orderDB.displayOrder().get(i);
                // get all parameters from pizzaOrder object
                name = pizzaOrder.getName();
                phone = pizzaOrder.getPhone();
                pizzaSize = pizzaOrder.getPizzaSize();
                toppings = pizzaOrder.getToppings();
                delivery = pizzaOrder.isDelivery();
                price = pizzaOrder.getPrice();
                // print them in the table format
                out.println("<tr><th>"+ name +"</th><th>" + phone + "</th><th>"
                        +pizzaSize +"</th><th>" +toppings+"</th><th>" + delivery+"</th><th>" + price+"</th></tr>");
            }
            // close tabel
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            // print exceptions
            System.out.println(ex);
        }finally{
            // close the PrintWriter
            out.close();
        }
    }
}
