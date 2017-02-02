package com.hms.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hms.utils.DBConnection;

/**
 * Servlet implementation class SetAppointment
 */
//@WebServlet("/saveAppointment")
public class SetAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn;
	PreparedStatement pstmt;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	try
    	{
    		conn = DBConnection.getDBConnection(config);
    		String sql = "insert into appointment_details (doctor_id, patient_id, appointment_date, appointment_time) values (?, ?, ?, ?)";
    		pstmt = conn.prepareStatement(sql);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
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
		
		try
		{
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			String appointmentDate = request.getParameter("appointmentDate");
			String appointmentTime = request.getParameter("appointmentTime");
			
			int count=0;
			Statement stmt = conn.createStatement();
			String query = "select count(*) from appointment_details where doctor_id="+doctorId+ " and appointment_date='"+appointmentDate +"' and appointment_time='"+appointmentTime+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next())
				count = rs.getInt(1);
			if(count == 0)
			{
				pstmt.setInt(1, doctorId);
				pstmt.setInt(2, patientId);
				pstmt.setDate(3, Date.valueOf(appointmentDate));
				pstmt.setString(4, appointmentTime);
				
				pstmt.executeUpdate();
				response.getWriter().write("Your appointment has been fixed.");
			}
			else
			{
				if(count >= 1)
				{
					query = "select count(*) from appointment_details where doctor_id="+doctorId+ " and appointment_date='"+appointmentDate +"'";
					rs = stmt.executeQuery(query);
					int totalCnt=0;
					if(rs.next())
						totalCnt = rs.getInt(1);
					if(totalCnt >= 2)
						response.getWriter().write("No appointments are available, Please try for next appointment.");
					else
						response.getWriter().write("Please try for other appointment.");
				}
			}
			
			response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
