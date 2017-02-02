package com.hms.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.hms.bean.DataBean;
import com.hms.utils.DBConnection;

/**
 * Servlet implementation class GetSpecialistDoctorList
 */
//@WebServlet("/getSpecialistDoctorList")
public class GetSpecialistDoctorList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpecialistDoctorList() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	conn = DBConnection.getDBConnection(config);
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
			String speciliazarion = request.getParameter("specialization"); 
			ResultSet rs=null;
			Statement stmt = conn.createStatement();
			
			String queryStr = "select id, doctor_name, available_time from doctor_master where specialization='"+speciliazarion+"'";
			rs = stmt.executeQuery(queryStr);

			List<DataBean> dataList = new ArrayList<DataBean>();
			
			while(rs.next())
			{
				DataBean data = new DataBean();
				data.setId(rs.getInt(1));
				data.setData(rs.getString(2)+"@"+rs.getString(3));
				dataList.add(data);
			}
			
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(dataList, new TypeToken<List<DataBean>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
