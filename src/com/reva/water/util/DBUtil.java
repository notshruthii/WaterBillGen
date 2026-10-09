package com.reva.water.util;
import java.sql.*;

public class DBUtil {
	public static Connection getDBConnection() throws Exception
	{
		// Load the driver and connect. Exceptions are propagated (not swallowed)
		// so the real cause is visible instead of a NullPointerException later.
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/WaterBillGen"; // MySQL port is 3306 (8080 is Tomcat)
		String user = "root";
		String pass = "Shruthi140499";
		return DriverManager.getConnection(url, user, pass);
	}
}