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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Misha
 */
public class PlaceOrder extends HttpServlet {
    
    
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        PizzaOrder pizzaOrder  = (PizzaOrder)session.getAttribute("pizzaOrder");
        OrderDB orderDB  = new OrderDB();
        String name = pizzaOrder.getName();
        String phone = pizzaOrder.getPhone();
        PrintWriter out = response.getWriter();
        
        
        String method = getServletConfig().getInitParameter("method");
        String action = getServletConfig().getInitParameter("action");
        //pick-up option
        String deliveryChoise = "\"deliveryChoise\"";
        //String selectName = getServletConfig().getInitParameter("selectPickup");
        String defaultName = "select";
        String pickUPName = "pickUP";
        String pickUPValue = "\"pickUP\"";
        String deliveryName = "delivery";
        String deliveryValue = "\"delivery\"";
        //radio buttons
        String radio = "\"radio\"";
        String radioName = "\"pizzaSize\"";
        String smallValue = "\"small\"";
        String smallName = "Small($5)";
        String mediumName = "Medium($7)";
        String mediumValue = "\"medium\"";
        String largeName = "Large($9)";
        String largeValue = "\"large\"";
        //checkboxes
        String checkbox = "\"checkbox\"";
        String checkboxName = "\"toppings\"";
        String toppingPepperoni = "\"Pepperoni\"";
        String toppingSausage = "\"Sausage\"";
        String toppingBSpinach = "\"Baby Spinach\"";
        String toppingPepper = "\"Pepper\"";
        String toppingNone = "\"None\"";
        String toppingPepperoniName = "Pepperoni";
        String toppingSausageName = "Sausage";
        String toppingBSpinachName = "Baby Spinach";
        String toppingPepperName = "Pepper";
        String toppingNoneName = "None";
        //submit button
        String submit = "\"submit\"";
        String submitValue = "\"Place My Order\"";
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>");
            out.println("<h1>Hi " + name +" </h1>");
            out.println("</br>");
            out.println("<h1>" + phone +" </h1>");
            out.println("<form method=" + method +" action=" + action +" >");
            ///////////////////////
            out.println("Pick up for delivery:<select name="+ deliveryChoise +" required>");
                //out.println("<option>"+ defaultName +"</option>" );
                out.println("<option value=" + pickUPValue +" selected>"+ pickUPName +"</option>" );
                out.println("<option value=" + deliveryValue +">"+ deliveryName+"</option>");
            out.println("</select>");
            ///////////////////////
            out.println("</br>");
            ///////////////////////
            out.println("Pizza Size:");
                out.println("<input type=" +radio+" name=" +radioName+" value="+smallValue+" checked/>"+smallName);
                out.println("<input type=" +radio+" name=" +radioName+" value="+mediumValue+"/>"+mediumName);
                out.println("<input type=" +radio+" name=" +radioName+" value="+largeValue+"/>"+largeName);
            ///////////////////////    
            out.println("</br>");   
            ///////////////////////
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingPepperoni+">"+toppingPepperoniName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingSausage+">"+toppingSausageName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingBSpinach+">"+toppingBSpinachName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingPepper+">"+toppingPepperName );
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingNone+">"+toppingNoneName );
            out.println("</br>");
            ///////////////////////
            out.println("<input type=" +submit+" value=" + submitValue+">");
            out.println("</form>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }finally{
            out.close();
        }
        
        
        
        
    }
    
    
    
    
}
