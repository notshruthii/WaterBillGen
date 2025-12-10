package com.reva.water.util;
import java.sql.*;
public class DBUtil {
	 public static Connection getDBConnection()throws Exception
	{
		 Connection con=null;
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
	     String url="jdbc:mysql://localhost:8080/WaterBillGen";
	     String user="root";
	     String pass= "Shruthi140499";
	     con=DriverManager.getConnection(url,user,pass);
	     return con;
	     }
		 catch(Exception e)
		 {
			 System.out.println("Connection to database failed!");
			 return null;
		 }	
	}
}
