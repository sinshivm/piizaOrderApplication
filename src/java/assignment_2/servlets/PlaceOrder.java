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
        // open the session started in BeginOrder
        HttpSession session = request.getSession();
        // get PizzaOrder class from session attribute 
        PizzaOrder pizzaOrder  = (PizzaOrder)session.getAttribute("pizzaOrder");
        // get values for drivaer,connURL,database,user and password for OrderDB class from context-param
        String driver = getServletContext().getInitParameter("driver");
        String connURL = getServletContext().getInitParameter("connURL");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password  = getServletContext().getInitParameter("password");
        // create an instance of OrderDB 
        OrderDB orderDB = new OrderDB(driver , connURL, database, user, password);
        // get name from PizzaOrder class 
        String name = pizzaOrder.getName();
        // get name from PizzaOrder class
        String phone = pizzaOrder.getPhone();
        // initialize PrintWriter
        PrintWriter out = response.getWriter();
        
        // assign type,values and names for information in the form
        // get method from init-param 
        String method = getServletConfig().getInitParameter("method");
        // get action from init-param
        String action = getServletConfig().getInitParameter("action");
        // SELECT
        // assign name for select tag
        // also assign names and values for delivery 
        String deliveryChoise = "\"deliveryChoise\"";
        String pickUPName = "pickUP";
        String pickUPValue = "\"pickUP\"";
        String deliveryName = "delivery";
        String deliveryValue = "\"delivery\"";
        // RADIO
        // assign type for input tag
        // assign name radio family
        // assign names nad values for raio buttons
        String radio = "\"radio\"";
        String radioName = "\"pizzaSize\"";
        String smallValue = "\"small\"";
        String smallName = "Small($5)";
        String mediumName = "Medium($7)";
        String mediumValue = "\"medium\"";
        String largeName = "Large($9)";
        String largeValue = "\"large\"";
        // CHECKBOX
        // assign type for input tag
        // assign name for checkbox family
        // assign names and values for properties in the checkbox input type
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
        // SUBMIT
        // assign type for input tag
        // assign name for submit button
        String submit = "\"submit\"";
        String submitValue = "\"Place My Order\"";
        try{
            // start html document fror form Place order
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Place Order</title>");
            // print the name of customer
            out.println("<h1>Hi " + name +" </h1>");
            out.println("</br>");
            // print the phone of cutomer
            out.println("<h1>" + phone +" </h1>");
            // create a form with already created method and action
            out.println("<form method=" + method +" action=" + action +" >");
            /////////////////////// create delivery section
            out.println("Pick up for delivery:<select name="+ deliveryChoise +" required>");
                out.println("<option></option>" );
                out.println("<option value='true'>"+ pickUPName +"</option>" );
                out.println("<option value='false'>"+ deliveryName+"</option>");
            out.println("</select>");
            ///////////////////////
            out.println("</br>");
            /////////////////////// create pizza size section
            out.println("Pizza Size:");
                out.println("<input type=" +radio+" name=" +radioName+" value="+smallValue+" checked/>"+smallName);
                out.println("<input type=" +radio+" name=" +radioName+" value="+mediumValue+"/>"+mediumName);
                out.println("<input type=" +radio+" name=" +radioName+" value="+largeValue+"/>"+largeName);
            ///////////////////////    
            out.println("</br>");   
            /////////////////////// create topping section
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingPepperoni+">"+toppingPepperoniName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingSausage+">"+toppingSausageName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingBSpinach+">"+toppingBSpinachName);
            out.println("</br>");
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingPepper+">"+toppingPepperName );
            out.println("<input type=" +checkbox+" name=" + checkboxName+" value=" + toppingNone+">"+toppingNoneName );
            out.println("</br>");
            /////////////////////// create submit button section
            out.println("<input type=" +submit+" value=" + submitValue+">");
            out.println("</form>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception ex){
            // print exceptions
            System.out.println(ex);
        }finally{
            // close the Printwriter
            out.close();
        }
        
        
        
        
    }
    
    
    
    
}
