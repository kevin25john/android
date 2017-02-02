<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Patient Registration Form</title>
		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css" type="text/css">
	  	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
	
		<script type="text/javascript">
			
			$(document).ready(function() {
				var date = new Date();
				 var currentMonth = date.getMonth();
				 var currentDate = date.getDate();
				var currentYear = date.getFullYear();
				$('#birthDate').datepicker({
					//minDate:0,
					maxDate:0,
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
			        changeYear: true,
			        //dateFormat: 'dd-mm-yy',
			        yearRange: '1900:'+currentYear,
			        //defaultDate:new Date(currentYear-18, currentMonth, currentDate),
					//maxDate: new Date(currentYear-18, currentMonth, currentDate),
					
				});
				
				/* $("#backBtn").click(function(){
					alert("hi");
					location.href="jsp/patientRegistration.jsp";
				}); */
				
			});
			
			function resetForm()
			{
				location.href="patientRegistration.jsp";
			}
			
			function backToLogin()
			{
				location.href="/HospitalManagement/";
			}
			
			function validation()
			{
				var flag = true;
				if($("#firstName").val().trim() == "" || $("#lastName").val().trim() == "" || $("#loginId").val().trim() == ""  
					|| $("#password").val().trim() == "" || $("#cpassword").val().trim() == "")
				{
					flag=false;
					alert("Please enter mandatory fields.");
				}
				
				if(flag && ($("#password").val().trim() != $("#cpassword").val().trim() ))
				{
					flag=false;
					alert("Password and Confirm Password should be same.");
				}
				return flag;
			}
		</script>
	</head>
	<body>
		<div style="padding: 35px 0px 0px 550px;">
			Patient Registration form
		</div>
		<div style="border: 0px solid blue;width: 400px;margin: 25px 500px">
			<form action="/HospitalManagement/patientRegistration" method="post">
				<table>
					<tr>
						<td>First Name<span style="color:red;">*</span></td>
						<td><input type="text" id="firstName" name="firstName"/></td>
					</tr>
					<tr>
						<td>Last Name<span style="color:red;">*</span></td>
						<td><input type="text" id="lastName" name="lastName"/> </td>
					</tr>
					<tr>
						<td>Login Id<span style="color:red;">*</span></td>
						<td><input type="text" id="loginId" name="loginId"/></td>
					</tr>
					<tr>
						<td>Password<span style="color:red;">*</span></td>
						<td><input type="password" id="password" name="password"/></td>
					</tr>
					<tr>
						<td>Confirm Password<span style="color:red;">*</span></td>
						<td><input type="password" id="cpassword" name="cpassword"/></td>
					</tr>
					<tr>
						<td>Birth Date</td>
						<td><input type="text" id="birthDate" name="birthDate"/> </td>
					</tr>
					<tr>
						<td>Address</td>
						<td><input type="textarea" id="address" name="address"/> </td>
					</tr>
					<tr>
						<td>State</td>
						<td><input type="text" id="state" name="state"/> </td>
					</tr>
					<tr>
						<td>Pincode</td>
						<td><input type="text" id="pincode" name="pincode"/> </td>
					</tr>
					<tr>
						<td>City</td>
						<td><input type="text" id="city" name="city"/> </td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>
							<select id="gender" name="gender" style="width:173px">
								<option value="M">Male</option>
								<option value="F">Female</option>
								<option value="U">Untagged</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Marital Status</td>
						<td>
							<select id="maritalStatus" name="maritalStatus" style="width:173px">
								<option value="M">Married</option>
								<option value="S">Single</option>
								<option value="W">Widow</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Email Id</td>
						<td><input type="text" id="emailId" name="emailId"/> </td>
					</tr>
					<!-- <tr>
						<td><input type="button" value="Back" id="backBtn" onclick="backToLogin();" /> </td>
						<td><input type="submit" value="Submit" id="submit" name="submit" onclick="return validation();"/> </td>
						<td><input type="button" value="Reset" id="resetBtn" name="resetBtn" onclick="resetForm();"/> </td>
					</tr> -->
				</table>
				<div style="margin: 12px 0px;">
						<span class="btnpadding"><input type="button" value="Back" id="backBtn" onclick="backToLogin();" class="buttonStyle"/> </span>
						<span class="btnpadding"><input type="submit" value="Submit" id="submit" name="submit" onclick="return validation();" class="buttonStyle"/> </span>
						<span class="btnpadding"><input type="button" value="Reset" id="resetBtn" name="resetBtn" onclick="resetForm();" class="buttonStyle"/> </span>
				</div>
			</form>
		</div>
	</body>
</html>