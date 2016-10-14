<%-- 
    Document   : index
    Created on : 07-Oct-2016, 18:48:35
    Author     : Misha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Welcome to my Pizza Ordering Application</h1>
        <form method="POST" action="BeginOrder.do" >
            Name: <input type="text" name="name" placeholder="Enter your name" required/>
            Phone: <input type="text" name="phone" placeholder="Enter your phone" required/>
            <input type="submit" value="Submit" />
        </form>
        <form method="POST" action="DisplayOrders.do">
            <input type="submit" value="Display Orders" />
        </form>
        
    </body>
</html>
