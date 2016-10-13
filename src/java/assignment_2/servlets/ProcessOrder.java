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
public class ProcessOrder extends  HttpServlet{
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // get session cretaed in the BeginOrder servlet
        HttpSession session = request.getSession();
        // get PizzaOrder class from session attributes
        PizzaOrder pizzaOrder = (PizzaOrder)session.getAttribute("pizzaOrder");
        // get driver,connURL, database,user and password values from context-param
        String driver = getServletContext().getInitParameter("driver");
        String connURL = getServletContext().getInitParameter("connURL");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password  = getServletContext().getInitParameter("password");
        // craete an instance of OrderDB class
        OrderDB orderDB = new OrderDB(driver , connURL, database, user, password);
        // create PrintWriter
        PrintWriter out = response.getWriter();
        
         
        // delivery
        // get values for delivery from form in PlaceOrder servlet
        String delivery = request.getParameter("deliveryChoise");
        if (delivery.equals("false")){
            pizzaOrder.setDelivery(false);
        }else if (delivery.equals("true")){
            pizzaOrder.setDelivery(true);
        }
        
        
        
        
        // radio buttons
        // get values fro prefered size from form in PlaceOrder servlet
        String selectedSize = request.getParameter("pizzaSize");
        // set values into the PizzaOrder class
        pizzaOrder.setPizzaSize(selectedSize);
        
        
        
        // toppings 
        // get the parameters for all toppings from form 
        // created in PlaceOrder servlet
        String[] toppings = request.getParameterValues("toppings");
        String selectedTopping = "";
        // get the length of the array
        int toppingsNo = toppings.length;
        // get all topping into one string
        for(int i = 0; i<toppingsNo;i++){
            if(i==(toppingsNo-1)){
                selectedTopping = selectedTopping +" "+  toppings[i] +".";
            }else{
                selectedTopping = selectedTopping +" "+  toppings[i] +",";
            }
        }
        // set values into the PizzaOrder class
        pizzaOrder.setToppings(selectedTopping);
        // call method which counts the total price
        countPrice(pizzaOrder , toppingsNo);
        
        // get name and phone of the customer from the instance of PizzaOrder class
        String name = pizzaOrder.getName();
        String phone = pizzaOrder.getPhone();
        
        
        try {
            // create a HTML page
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>");
            out.println("<h1>This is your order </h1>");
            // print the name of the customer
            out.println("<p>"+name+"</p>");
            // print the phone of the customer
            out.println("<p>"+phone+"</p>");
            out.println("<ul>");
            // print everything about the pizza
            out.println("<li>Delivery:"+pizzaOrder.isDelivery()+"</li>");
            out.println("<li>Size: " +selectedSize+" </li>");
            out.println("<li>Toppings:" +pizzaOrder.getToppings()+" </li>");
            out.println("<li>Price:" +pizzaOrder.getPrice()+" </li>");
            // a link to the index page
            out.println("<a href=\"index.jsp\">Return to main Page</a>");
            out.println("</body>");
            out.println("</html>");
            // add order to the database
            orderDB.addOrder(pizzaOrder);
        } catch (Exception ex) {
            // print the exceptions
            System.out.println(ex);
        }finally{
            // close the PrintWriter
            out.close();
        }
        
        //response.sendRedirect("index.jsp");
    }
    
    
    // method that counts the price of the pizza
    public  void countPrice(PizzaOrder pizzaOrder , int toppingsNo){
        // get delivery from pizzaOrder class
        boolean delivery = pizzaOrder.isDelivery();
        // craete a variable for price 
        double price = 0;
        // get pizza size
        String size = pizzaOrder.getPizzaSize();
        // get pizza toppings
        String none = pizzaOrder.getToppings();
        // assign price for each size of pizza
        if(size.equals("small")){
            price= price+5.00;
        }else if(size.equals("medium")){
            price=price+7.00;
        }else if(size.equals("large")){
            price=price+9.00;
        }
        // add the price of each topping to the pizza price
        price+=toppingsNo;
        
        // check the delivery options
        // add 2 dollars if its deluvery
        if(delivery == true){
            price = price;
        }else if(delivery == false){
            price+=2;
        }
        // set value of price to the PizzaOrder class
        pizzaOrder.setPrice(price);
        
    }
}
