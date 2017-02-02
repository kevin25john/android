package com.hms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hms.bean.DoctorBean;
import com.hms.utils.DBConnection;

/**
 * Servlet implementation class Registration
 */
//@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private PreparedStatement pstmt;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		try
		{
			/*ServletContext context = config.getServletContext();
			String driverName = context.getInitParameter("driverName");
			String url = context.getInitParameter("url");
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, "system", "system");*/
			conn = DBConnection.getDBConnection(config);
			String queryStr = "insert into  doctor_master (user_id, password, doctor_name, Age, qualification, specialization, "
					+ "experience, achiivement, address, mobile, email_id, available_time, available_day) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(queryStr);
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
	
		/*PrintWriter out = response.getWriter();
		out.print("count: yes");*/
		
		ResultSet rs=null;
		DoctorBean bean = new DoctorBean();
		
		String doctorName = request.getParameter("doctorName");
		String loginId = request.getParameter("loginId");
		String pwd = request.getParameter("password");
		String age = request.getParameter("age");
		String qualification = request.getParameter("qualification");
		String specialization = request.getParameter("specialization");
		String experience = request.getParameter("experience");
		String achievement = request.getParameter("achievement");
		String address = request.getParameter("address");
		String mobileNo = request.getParameter("mobileNo");
		String emailId = request.getParameter("emailId");		
		String availableTime = request.getParameter("availableTime");
		String availableDay = request.getParameter("availableDay");
		
		
		bean.setDoctorName(request.getParameter("doctorName"));
		bean.setUserId(request.getParameter("loginId"));
		bean.setPassword(request.getParameter("password"));
		bean.setAge(age != null && age != ""? Integer.parseInt(age):0);
		bean.setQualification(request.getParameter("qualification"));
		bean.setSpecialization(request.getParameter("specialization"));
		bean.setExperience(experience != null && experience != ""? Integer.parseInt(experience):0);
		bean.setAchiivement(request.getParameter("achievement"));
		bean.setAddress(request.getParameter("address"));
		bean.setMobile(mobileNo != null && mobileNo != ""? Long.parseLong(mobileNo):0);
		bean.setEmailId(request.getParameter("emailId"));		
		bean.setAvailableTime(request.getParameter("availableTime"));
		bean.setAvailableDay(request.getParameter("availableDay"));
		int slotCount=0;
		
		try
		{
			Statement stmt = conn.createStatement();
			String queryStr = "select count(*) from doctor_master where available_time='"+availableTime+"' and available_day='"+availableDay+"'";
			rs = stmt.executeQuery(queryStr);
			if(rs.next())
				slotCount=rs.getInt(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(slotCount == 0)
		{
			try
			{
				pstmt.setString(1, loginId);
				pstmt.setString(2, pwd);
				pstmt.setString(3, doctorName);
				pstmt.setInt(4, Integer.parseInt(age!=""?age:"0"));
				pstmt.setString(5, qualification);
				pstmt.setString(6, specialization);
				pstmt.setInt(7, Integer.parseInt(experience!=""?experience:"0"));
				pstmt.setString(8, achievement);
				pstmt.setString(9, address);
				pstmt.setLong(10, Long.parseLong(mobileNo!=""?mobileNo:"0"));
				pstmt.setString(11, emailId);
				pstmt.setString(12, availableTime);
				pstmt.setString(13, availableDay);
				
				int count = pstmt.executeUpdate();
				//System.out.println(pstmt.executeUpdate());
				PrintWriter out = response.getWriter();
				out.print("count: "+count);
				
				request.setAttribute("loginMsg", "Please login with register user id and password");
				//sgetServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("isInvalid", "Their is some error while the saving data.");
				//request.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
				request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
			}		
		}
		else
		{
			request.setAttribute("isInvalid", "This time slot is already allotted.");
			request.setAttribute("bean", bean);
			request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
			//request.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
		}
	}

}
