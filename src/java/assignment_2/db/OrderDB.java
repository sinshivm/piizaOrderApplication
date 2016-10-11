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
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String connURL = "jdbc:mysql://localhost/";
    private static String database = "pizza_orders";
    private static String user = "root";
    private static String password = "M23121997m";
    
    
    private static final String TABLE_NAME= "OrderInfo";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String PIZZA_SIZE = "pizzaSize";
    private static final String TOPPINGS = "toppings";
    private static final String DELIVERY = "delivery";
    private static final String PRICE = "price";
    
    
    
    public void addOrder(PizzaOrder pizzaOrder) throws Exception{
        
        String sql = "INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?)";
        
        String formatSql = String.format(sql, TABLE_NAME,NAME,PHONE,PIZZA_SIZE,TOPPINGS,DELIVERY,PRICE);
        
        String name = pizzaOrder.getName();
        String phone = pizzaOrder.getPhone();
        String pizzaSize = pizzaOrder.getPizzaSize();
        String toppings = pizzaOrder.getToppings();
        boolean delivery = pizzaOrder.isDelivery();
        double price = pizzaOrder.getPrice();
        
        Connection conn = null;
        PreparedStatement ps = null;
        int result= 0;
        try {
            conn = DBConnector.getConnection(driver, connURL, database, user, password);
            
            ps = conn.prepareStatement(formatSql);
            
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, pizzaSize);
            ps.setString(4, toppings);
            ps.setBoolean(5, delivery);
            ps.setDouble(6, price);
            
            ps.executeUpdate();
        } catch (Exception ex) {
            throw(ex);
        }
       
    }
    
    
    
    public ArrayList<PizzaOrder> displayOrder(PizzaOrder pizzaOrder) throws Exception {
        
        String formatSql = "SELECT * FROM %s WHERE %s!='chef'";
        
        String sql = String.format(formatSql, TABLE_NAME, NAME);
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ArrayList<PizzaOrder> orders = new ArrayList<PizzaOrder>();
        
        try {
            conn = DBConnector.getConnection(driver, connURL, database, user, password);
            
            ps = conn.prepareStatement(sql);
            
            rs  = ps.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String pizzaSize = rs.getString("pizzaSize");
                String toppings = rs.getString("topings");
                boolean delivery = rs.getBoolean("delivery");
                double price = rs.getDouble("price");
                pizzaOrder = new PizzaOrder(name,phone,pizzaSize,toppings,delivery,price);
                
                orders.add(pizzaOrder);
            }
        } catch (Exception ex) {
            throw(ex);
        }finally{
            DBConnector.closeJDBCObjects(conn, ps, rs);
        }
        
        return orders;
        
    }
}
