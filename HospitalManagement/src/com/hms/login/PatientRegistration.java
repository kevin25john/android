package com.hms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hms.utils.DBConnection;

/**
 * Servlet implementation class PatientRegistration
 */
//@WebServlet("/patientRegistration")
public class PatientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	PreparedStatement pstmt;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {

		try
		{
			conn = DBConnection.getDBConnection(config);
			String queryStr = "insert into  patient_master (user_id, password, firstName, lastName, birtDate, gender, "
					+ "maritalStatus, address, city, state, pincode, email_id) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String loginId = request.getParameter("loginId");
		String pwd = request.getParameter("password");
		String birthDate = request.getParameter("birthDate");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pincode = request.getParameter("pincode");
		String gender = request.getParameter("gender");
		String emailId = request.getParameter("emailId");
		String maritalStatus = request.getParameter("maritalStatus");
		
		try
		{
			pstmt.setString(1, loginId);
			pstmt.setString(2, pwd);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setString(5, birthDate);
			pstmt.setString(6, gender);
			pstmt.setString(7, maritalStatus);
			pstmt.setString(8, address);
			pstmt.setString(9, city);
			pstmt.setString(10, state);
			pstmt.setLong(11, Long.parseLong(pincode != ""? pincode: "0"));
			pstmt.setString(12, emailId);
			
			int count = pstmt.executeUpdate();
			//System.out.println(pstmt.executeUpdate());
			PrintWriter out = response.getWriter();
			out.print("count: "+count);
			
			request.setAttribute("loginMsg", "Please login with register user id and password");
			request.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("isInvalid", "Their is some while the saving data.");
			request.getServletContext().getRequestDispatcher("/jsp/patientRegistration.jsp").forward(request, response);
		}
	}

}
