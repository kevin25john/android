package com.hms.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class DBConnection {

	public static Connection getDBConnection(ServletConfig config)
	{
		Connection conn=null;
		try
		{
			ServletContext context = config.getServletContext();
			String driverName = context.getInitParameter("driverName");
			String url = context.getInitParameter("url");
			Class.forName(driverName);
			//conn = DriverManager.getConnection(url, "system", "system");
			conn = DriverManager.getConnection(url);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
