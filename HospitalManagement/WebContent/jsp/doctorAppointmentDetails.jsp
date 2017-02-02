<%@page import="com.hms.bean.AppointmentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
	
	<!-- <style type="text/css">
	
		.fixSize {width:175px;height:22px;}
		table {border-collapse: collapse;width: 100%;}
		th { background-color: #4CAF50;
    		 color: white;}
		th, td {
    		padding: 5px;
    		text-align: left;
		}
	</style> -->
	
</head>
<body>
	<%-- <%=session.getAttribute("doctorName") %> --%>
	<div style="padding: 25px 0px 0px 550px;">
		Doctor Appointment Details
		<span style="padding: 0px 0px 0px 345px;"><a href="logout">Logout</a></span>
	</div>
	
	<div style="margin: 25px 200px;">
		<table border="1">
		<tr>
			<th>Doctor Name</th>
			<th>Patient Name</th>
			<th>Birth Date</th>
			<th>Gender</th>
			<!-- <th>Marital status</th> -->
			<th>Appointment Date</th>
			<th>Appointment Time</th>
		</tr>
		
		<% 
			List<AppointmentBean> appList = (List<AppointmentBean>)request.getAttribute("appointmentDetails");
			for(int i=0; i<appList.size(); i++)
			{
				AppointmentBean bean = appList.get(i);
		%>
				<tr>
					<td><%=bean.getDoctorName() %></td>
					<td><%=bean.getFirstName()+" "+bean.getLastName() %></td>
					<td><%=bean.getBirthDate() %></td>
					<td><%=bean.getGender() %></td>
					<%-- <td><%=bean.getMaritalStatus() %></td> --%>
					<td><%=bean.getAppointmentDate() %></td>
					<td><%=bean.getAppointmentTime() %></td>
				</tr>		
		<%
			}
		%>
		</table>
	</div>
	<!-- <div style="margin: 200px 100px;border: 1px solid red;">
		<a href="logout">Logout</a>
	</div> -->
</body>
</html>