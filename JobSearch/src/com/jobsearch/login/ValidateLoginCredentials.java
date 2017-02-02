package com.jobsearch.login;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateLoginCredentials
 */
//@WebServlet("/validateLoginCredentials")
public class ValidateLoginCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	
    /**
     * Default constructor. 
     */
    public ValidateLoginCredentials() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	
    	try
		{
			ServletContext context = config.getServletContext();
			String driverName = context.getInitParameter("driverName");
			String url = context.getInitParameter("url");
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, "system", "system");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(conn);
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
        
        ObjectOutputStream out=new ObjectOutputStream(response.getOutputStream());
        
		/*PrintWriter printWriter = response.getWriter();
		printWriter.print("ys");*/
		System.out.println("Yes");
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pwd");
		ResultSet rs = null;
		
		try
		{
			Statement stmt = conn.createStatement();
			String query = "select * from user_master where user_name='"+user
					+"' and password='"+pass+"'";
			
			rs = stmt.executeQuery(query);
			int userId = 0; 
			while(rs.next())
			{
				userId = rs.getInt("user_id");
			}
			
			if(userId != 0)
			{
				out.writeObject("1");
			}
			else
				out.writeObject("0");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		System.out.println(user+" "+pass);
		
		
	}

}
