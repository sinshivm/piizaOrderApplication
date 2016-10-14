/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_2.db;

import assignment_2.PizzaOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Misha
 */
public class OrderDB {
    
    
    // create variables for values from context-param
    String driver;
    String connURL;
    String database;
    String user;
    String password;
    // create the fields in the table 
    private static final String TABLE_NAME= "OrderInfo";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String PIZZA_SIZE = "pizzaSize";
    private static final String TOPPINGS = "toppings";
    private static final String DELIVERY = "delivery";
    private static final String PRICE = "price";
    
    
    // create a constructor
    public OrderDB(String driver, String connURL, String database, String user, String password) {
        this.driver = driver;
        this.connURL = connURL;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    // addorder method to add order to the database
    public void addOrder(PizzaOrder pizzaOrder) throws Exception{
        // create protected sql string
        String sql = "INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?)";
        // format protected sql string
        String formatSql = String.format(sql, TABLE_NAME,NAME,PHONE,PIZZA_SIZE,TOPPINGS,DELIVERY,PRICE);
        // get all values from PizzaOrder class
        String name = pizzaOrder.getName();
        String phone = pizzaOrder.getPhone();
        String pizzaSize = pizzaOrder.getPizzaSize();
        String toppings = pizzaOrder.getToppings();
        boolean delivery = pizzaOrder.isDelivery();
        double price = pizzaOrder.getPrice();
        // craete conncetion
        Connection conn = null;
        // crreate prepared statement
        PreparedStatement ps = null;
        // create variable for number of rows affected
        int result= 0;
        try {
            // establish conncetion to the database
            conn = DBConnector.getConnection(driver, connURL, database, user, password);
            // prepare statement
            ps = conn.prepareStatement(formatSql);
            // add values from PizzaOrder class to the prepared sql
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, pizzaSize);
            ps.setString(4, toppings);
            ps.setBoolean(5, delivery);
            ps.setDouble(6, price);
            // execute update
            ps.executeUpdate();
        } catch (Exception ex) {
            // throw exceptions
            throw(ex);
        }finally{
            // close conncetion and PreparedStatement
            DBConnector.closeJDBCObjects(conn, ps);
        }
       
    }
    
    
    // create a method which will display everything from the database
    public ArrayList<PizzaOrder> displayOrder() {
        // create protectec sql string
        String formatSql = "SELECT * FROM %s";
        // format protected sql string
        String sql = String.format(formatSql, TABLE_NAME);
        // create conncetion
        Connection conn = null;
        // craete prepared statement
        PreparedStatement ps = null;
        // create result set
        ResultSet rs = null;
        // create an array list which stores the instances of PizzaOrder class
        ArrayList<PizzaOrder> orders = new ArrayList<PizzaOrder>();
        
        try {
            // establish the conncetion
            conn = DBConnector.getConnection(driver, connURL, database, user, password);
            // prepare the statemnet
            ps = conn.prepareStatement(sql);
            // execute query
            rs  = ps.executeQuery();
            // while result set has something in it do the following
            while(rs.next()){
                // get name from the table
                String name = rs.getString(NAME);
                // get phone from the table
                String phone = rs.getString(PHONE);
                // get pizza size from the table
                String pizzaSize = rs.getString(PIZZA_SIZE);
                // get topping from the table
                String toppings = rs.getString(TOPPINGS);
                // get delivery option from the table
                boolean delivery = rs.getBoolean(DELIVERY);
                // get price from the table
                double price = rs.getDouble(PRICE);
                // assign this values to the new instance of PizzaOrder class
                PizzaOrder pizzaOrder = new PizzaOrder(name,phone,pizzaSize,toppings,delivery,price);
                // add the new instance to the arraylist
                orders.add(pizzaOrder);
            }
        } catch (Exception ex) {
            // throw the exceptions
            System.out.println(ex);
        }finally{
            // close conncetion , prepare statement nad result set
            DBConnector.closeJDBCObjects(conn, ps, rs);
        }
        // return the arrayList of inctances of the PizzaOrder class
        return orders;
        
    }
}
