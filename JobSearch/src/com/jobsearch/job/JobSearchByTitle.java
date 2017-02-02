package com.jobsearch.job;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JobSearchByTitle
 */
//@WebServlet("/jobSearchByTitle")
public class JobSearchByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private Connection conn;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//System.out.println(conn+"  ");
		try
		{
			ServletContext context = getServletContext();
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
     * @see HttpServlet#HttpServlet()
     */
    public JobSearchByTitle() {
        super();
        // TODO Auto-generated constructor stub
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
		
		/*response.setContentType("text/plain;");
		response.setHeader("Expect", "100-continue");*/
        ObjectOutputStream out=new ObjectOutputStream(response.getOutputStream());
        
		/*PrintWriter printWriter = response.getWriter();
		printWriter.print("ys");*/
		System.out.println("Yes jobs");
		
		String jobTitle = request.getParameter("jobTitle");
		ResultSet rs = null;
		String jobDesc = "";
		
		try
		{
			Statement stmt = conn.createStatement();
			String query = "select * from (select * from job_details where jobTitle like '"+jobTitle
					+"%' order by 4 desc) where rownum=1";
			
			rs = stmt.executeQuery(query);
			 
			while(rs.next())
			{
				jobDesc = rs.getString("jobDesc");
			}
			
			if(jobDesc != null && !jobDesc.equals(""))
			{
				out.writeObject(jobDesc);
			}
			else
				out.writeObject("0");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("jobDesc: "+jobDesc);
		
	}

}
