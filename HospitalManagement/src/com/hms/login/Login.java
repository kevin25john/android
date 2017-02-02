package com.hms.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hms.bean.AppointmentBean;
import com.hms.utils.DBConnection;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		conn = DBConnection.getDBConnection(config);
		System.out.println(conn);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginType = request.getParameter("loginType");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		ResultSet rs = null;
		System.out.println(loginType);
		String queryStr = "";
		try
		{
			Statement stmt = conn.createStatement();
			
			if(loginType.equalsIgnoreCase("doctor"))
			{
				String doctorId = "";
				queryStr = "select * from doctor_master where user_id='"+loginId+"' and password='"+password+"'";
				rs = stmt.executeQuery(queryStr);
				//SessionBean sessionBean = new SessionBean();
				if(rs.next())
				{
					HttpSession session = request.getSession();
					System.out.println(rs.getString(1)+" ");
					doctorId = rs.getString("id");
					session.setAttribute("userId", rs.getString("user_id"));
					session.setAttribute("pwd", rs.getString("password"));
					session.setAttribute("doctorName", rs.getString("doctor_name"));
					/*sessionBean.setUserId(rs.getString("user_id"));
					sessionBean.setPassword(rs.getString("password"));
					sessionBean.setUserName(rs.getString("doctor_name"));*/
				}
				if(doctorId != "")
				{
					queryStr = "select dm.doctor_name, pm.firstName, pm.lastName, pm.birtDate, pm.gender, ad.appointment_date, ad.appointment_time "
							+ "from appointment_details ad left join doctor_master dm on dm.id = ad.doctor_id "
							+ "left join patient_master pm on pm.id = ad.patient_id where dm.id ="+doctorId;
					rs = stmt.executeQuery(queryStr);
					List<AppointmentBean> appList = new ArrayList<AppointmentBean>();
					while(rs.next())
					{
						AppointmentBean doctor = new AppointmentBean();
						doctor.setDoctorName(rs.getString(1));
						doctor.setFirstName(rs.getString(2));
						doctor.setLastName(rs.getString(3));
						doctor.setBirthDate(rs.getString(4));
						doctor.setGender(rs.getString(5));
						doctor.setAppointmentDate(rs.getString(6).substring(0, 10));
						doctor.setAppointmentTime(rs.getString(7));
						appList.add(doctor);						
					}
					
					
					//session.setAttribute("SessionBean", sessionBean);
					request.setAttribute("appointmentDetails", appList);
					
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/doctorAppointmentDetails.jsp");
					rd.forward(request, response);
					
					//getServletContext().getRequestDispatcher("/jsp/doctorAppointmentDetails.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("isInvalid", "Invalid user id and password");
					getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
				}
			}
			else if(loginType.equalsIgnoreCase("patient"))
			{
				String patientId = "";
				queryStr = "select * from patient_master where user_id='"+loginId+"' and password='"+password+"'";
				rs = stmt.executeQuery(queryStr);
				//SessionBean sessionBean = new SessionBean();
				HttpSession session = request.getSession();
				if(rs.next())
				{
					System.out.println(rs.getString(1)+" ");
					String gender = rs.getString("gender");
					session.setAttribute("userId", rs.getString("user_id"));
					session.setAttribute("pwd", rs.getString("password"));
					session.setAttribute("firstName", rs.getString("firstName"));
					session.setAttribute("patientId", rs.getString("ID"));
					session.setAttribute("patientName", rs.getString("firstName")+" "+rs.getString("lastName"));
					//session.setAttribute("lastName", );
					session.setAttribute("birthDate", rs.getString("birtDate"));
					session.setAttribute("gender", gender != null && gender.equals("M")?"Male":"Female");
					patientId = rs.getString("ID");
					/*sessionBean.setUserId(rs.getString("user_id"));
					sessionBean.setPassword(rs.getString("password"));
					sessionBean.setUserName(rs.getString("firstName"));*/
				}
				if(patientId != "")
				{
					queryStr = "select dm.doctor_name, pm.firstName, pm.lastName, pm.birtDate, pm.gender, ad.appointment_date, ad.appointment_time "
							+ "from appointment_details ad left join doctor_master dm on dm.id = ad.doctor_id "
							+ "left join patient_master pm on pm.id = ad.patient_id where pm.id ="+patientId;
					rs = stmt.executeQuery(queryStr);
					List<AppointmentBean> appList = new ArrayList<AppointmentBean>();
					while(rs.next())
					{
						AppointmentBean doctor = new AppointmentBean();
						doctor.setDoctorName(rs.getString(1));
						doctor.setFirstName(rs.getString(2));
						doctor.setLastName(rs.getString(3));
						doctor.setBirthDate(rs.getString(4));
						doctor.setGender(rs.getString(5)!=null?(rs.getString(5).equals("F")?"Female":"Male"):"");
						doctor.setAppointmentDate(rs.getString(6).substring(0, 10));
						doctor.setAppointmentTime(rs.getString(7));
						appList.add(doctor);						
					}
					
					//session.setAttribute("SessionBean", sessionBean);
					request.setAttribute("appointmentDetails", appList);
					
					getServletContext().getRequestDispatcher("/jsp/patientsAppointmentDetails.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("isInvalid", "Invalid user id and password");
					getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
					
					//getServletContext().getRequestDispatcher("/jsp/patientsAppointmentDetails.jsp").forward(request, response);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("Yes..");
		/*PrintWriter out = response.getWriter();
		out.print("Yees");*/
	}

}
