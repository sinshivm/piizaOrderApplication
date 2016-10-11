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
        
        HttpSession session = request.getSession();
        
        PizzaOrder pizzaOrder = (PizzaOrder)session.getAttribute("pizzaOrder");
        OrderDB orderDB = new OrderDB();
        PrintWriter out = response.getWriter();
        
         
        //delivery
        String delivery = request.getParameter("deliveryChoise");
        if(delivery=="pickUP"){
            pizzaOrder.setDelivery(true);
        }else if(delivery=="delivery"){
            pizzaOrder.setDelivery(false);
        }
        //radio buttons
        String selectedSize = request.getParameter("pizzaSize");
        pizzaOrder.setPizzaSize(selectedSize);
        //toppings 
        //ArrayList toppings = new ArrayList();
        String[] toppings = request.getParameterValues("toppings");
        String selectedTopping = "";
        int toppingsNo = toppings.length;
        for(int i = 0; i<toppingsNo;i++){
            if(i==(toppingsNo-1)){
                selectedTopping = selectedTopping +" "+  toppings[i] +".";
            }else{
                selectedTopping = selectedTopping +" "+  toppings[i] +",";
            }
        }
        pizzaOrder.setToppings(selectedTopping);
        
        countPrice(pizzaOrder , toppingsNo);
        
        
        String name = pizzaOrder.getName();
        String phone = pizzaOrder.getPhone();
        
        
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>");
            out.println("<h1>This is your order </h1>");
            out.println("<p>"+name+"</p>");
            out.println("<p>"+phone+"</p>");
            out.println("<ul>");
            out.println("<li>Delivery:"+delivery+"</li>");
            out.println("<li>Size: " +selectedSize+" </li>");
            out.println("<li>Toppings:" +pizzaOrder.getToppings()+" </li>");
            out.println("<li>Price:" +pizzaOrder.getPrice()+" </li>");
            out.println("<a href=\"index.jsp\">Return to main Page</a>");
            out.println("</body>");
            out.println("</html>");
            orderDB.addOrder(pizzaOrder);
        } catch (Exception ex) {
            System.out.println(ex);
        }finally{
            out.close();
        }
        
        //response.sendRedirect("index.jsp");
    }
    
    
    
    public  void countPrice(PizzaOrder pizzaOrder , int toppingsNo){
        
        boolean delivery = pizzaOrder.isDelivery();
        double price = 0;
        String size = pizzaOrder.getPizzaSize();
        String none = pizzaOrder.getToppings();
        if(size.equals("small")){
            price= price+5.00;
        }else if(size.equals("medium")){
            price=price+7.00;
        }else if(size.equals("large")){
            price=price+9.00;
        }
        if (none.equals("None")){
            price = price;
        }else{
            price+=toppingsNo;
        }
        
        if(delivery==true){
            price = price;
        }else{
            price = price+ 2.00;
        }
        
        pizzaOrder.setPrice(price);
        
    }
}
