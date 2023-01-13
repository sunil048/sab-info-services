<%@ page import="java.sql.*" %>

<html>

<body>

    Hi welcome to JSP..................
    <br>
    <hr>
    <%
       out.println("welcome");
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_sabwiki", "dev","dev@123");
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select * from books");
       while (rs.next()){
    	   out.println("Book Name : "+rs.getString("bookname"));
       }
    %>
    <h3>Bokk list is </h3>
    <table border="1px solid">
  		<tr style="background-color:red;">
  			<th>Book Name</th>
  			<th>Created By</th>
  		</tr>
  		  <%
       
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_sabwiki", "dev","dev@123");
       Statement stmt1 = conn1.createStatement();
       ResultSet rs1 = stmt1.executeQuery("select * from books");
       while (rs1.next()){
    	  %>
    	  <tr>
    	   	<td>
    	  <%
    	   out.println(rs1.getString("bookname"));
       
      
   		 %>
    </td>
    <td>
    <%
    	   out.println(rs1.getString("created_by"));
       }
      
   		 %>
    </td>
    </tr>
    </table>
   
</body>
</html>