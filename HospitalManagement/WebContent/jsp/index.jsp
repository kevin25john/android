<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login to HMS</title>
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
		
		<script type="text/javascript">
			function validation() {
				//alert(document.getElementById("loginType").value);
				var flag = true;
				if(document.getElementById("loginType").value == "" )
					flag=false;
				else if(document.getElementById("loginId").value == "")
					flag=false;
				else if(document.getElementById("password").value == "")
					flag=false;
				
				if(!flag)
					alert("Please select Login Type, Login Id and Password.");
				
				return flag;
			}
		</script>
	</head>
	<body>
		<div style="margin: 0px 0px 0px 1050px;border: 0px solid red;">
			<a href="jsp/registration.jsp">Doctor's Registration</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="jsp/patientRegistration.jsp">Patiient's Registration</a>
		</div>
		<div style="border: 0px solid blue;width: 300px;margin: 150px 0px 0px 500px;">
			<form action="login" method="post">
				<table>
					<% 
						String isInvalid = (String) request.getAttribute("isInvalid");
						if(isInvalid != null && !isInvalid.equals("")){
					%>
					<tr>
						<td colspan="2">
							<span style="color: red;"> <%=isInvalid%> </span>
						</td>
					</tr>
					<% } %>
					<% 
						String loginMsg = (String) request.getAttribute("loginMsg");
						if(loginMsg != null && !loginMsg.equals("")){
					%>
					<tr>
						<td colspan="2">
							<span style="color: blue;"> <%=loginMsg%> </span>
						</td>
					</tr>
					<% } %>
					<tr>
						<td>Login Type</td>
						<td>
							<select id="loginType" name="loginType" style="width:175px;height: 20px;text-align: center;">
								<option value="">Select</option>
								<option value="doctor">Doctor</option>
								<option value="patient">Patient</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Login Id</td>
						<td><input type="text" id="loginId" name="loginId" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" id="password" name="password"/></td>
					</tr> 
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" id="submit" name="submit" onclick="return validation();" class="buttonStyle"/></td>
					</tr>
				</table>
			</form>
		</div>
		
	</body>
</html>