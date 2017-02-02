<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"> </script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Doctor's Registration Form</title>
		
		<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
  		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
		
		
		<script type="text/javascript">
			
			<%-- $(document).ready(function() {
				
				document.getElementById("backBtn").click(function(){
					alert("hi");
					location.href="jsp/patientRegistration.jsp";
				});
				alert("<%= request.getAttribute("isInvalid")%>");
				var invalidMsg = "<%= request.getAttribute("isInvalid")%>";
				$("#msg").remove();
				if(invalidMsg != null && invalidMsg != "")
				{
					$("#msg").attr("style", "color:red;")
					$("#msg").append(invalidMsg);
				}
				
			}); --%>
			
			$(document).ready(function() {
				//alert("${bean.specialization}");
				if('${bean.specialization}' != null && '${bean.specialization}'.trim() != "")
				{
					$("#specialization").val('${bean.specialization}');
				}
			});
			
			function resetForm()
			{
				alert("${pageContext.request.contextPath}");
				location.href="<%=request.getContextPath()%>/jsp/registration.jsp";
			}
			
			function backToLogin()
			{
				location.href="/HospitalManagement/";
			}
			
			function validation()
			{
				//alert("Hi.."+document.getElementById("doctorName").value);
				var flag = true;
				if(document.getElementById("doctorName").value.trim() == "" || document.getElementById("loginId").value.trim() == "" || document.getElementById("password").value.trim() == "" || document.getElementById("cpassword").value.trim() == "" )
				{
					flag=false;
					alert("Please enter mandatory fields.");
				}
				
				if(flag && (document.getElementById("password").value.trim() != document.getElementById("cpassword").value.trim() ))
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
			Doctor Registration form
		</div>
		<div id="msg" style="width: 225px;margin: 15px 0px 0px 500px;color: red;"><%= request.getAttribute("isInvalid") != null? request.getAttribute("isInvalid") : ""%></div>
		<div style="border: 0px solid blue;width: 400px;margin: 25px 500px">
			<form action="/HospitalManagement/registration" method="post">
				<table>
					<tr>
						<td>Doctor Name <span style="color:red;">*</span></td>
						<td><input type="text" id="doctorName" name="doctorName" value="${bean.doctorName}"/></td>
					</tr>
					<tr>
						<td>Login Id<span style="color:red;">*</span></td>
						<td><input type="text" id="loginId" name="loginId" value="${bean.userId}"/></td>
					</tr>
					<tr>
						<td>Password<span style="color:red;">*</span></td>
						<td><input type="password" id="password" name="password" value="${bean.password}" /></td>
					</tr>
					<tr>
						<td>Confirm Password<span style="color:red;">*</span></td>
						<td><input type="password" id="cpassword" name="cpassword" value="${bean.password}" /></td>
					</tr>
					<tr>
						<td>Age</td>
						<td><input type="text" id="age" name="age" value="${bean.age}" /></td>
					</tr>
					<tr>
						<td>Qualification</td>
						<td><input type="text" id="qualification" name="qualification" value="${bean.qualification}" /></td>
					</tr>
					<tr>
						<td>Specialization<span style="color:red;">*</span></td>
						<td>
							<!-- <input type="text" id="specialization" name="specialization"/> -->
							<select id="specialization" name="specialization" class="fixSize" >
								<option value="Pediatrician">Pediatrician</option>
								<option value="Opthalmologist">Opthalmologist</option>
								<option value="Dermatologist">Dermatologist</option>
								<option value="Cardiologist">Cardiologist</option>
								<option value="Psychiatrist">Psychiatrist</option>
								<option value="General Surgeon">General Surgeon</option>
								<option value="Dentist">Dentist</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>Experience</td>
						<td><input type="text" id="experience" name="experience" value="${bean.experience}" /></td>
					</tr>
					<tr>
						<td>Achievement</td>
						<td><input type="text" id="achievement" name="achievement" value="${bean.achiivement}" /></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><input type="text" id="address" name="address" value="${bean.address}" /></td>
					</tr>
					<tr>
						<td>Mobile No.</td>
						<td><input type="text" id="mobileNo" name="mobileNo" value="${bean.mobile}" /></td>
					</tr>
					<tr>
						<td>Email Id</td>
						<td><input type="text" id="emailId" name="emailId" value="${bean.emailId}" /></td>
					</tr>
					<tr>
						<td>Available Timings<span style="color:red;">*</span></td>
						<td>
							<!-- <input type="text" id="availableTime" name="availableTime"/> -->
							<select id="availableTime" name="availableTime" class="fixSize">
								<option value="10.00-11.00 am">10.00-11.00 am</option>
								<option value="11.00-12.00 am">11.00-12.00 am</option>
								<option value="12.00-1.00 pm">12.00-1.00 pm</option>
								<option value="1.00-2.00 pm">1.00-2.00 pm</option>
								<option value="2.00-3.00 pm">2.00-3.00 pm</option>
								<option value="3.00-4.00 pm">3.00-4.00 pm</option>
								<option value="4.00-5.00 pm">4.00-5.00 pm</option>
								<option value="5.00-6.00 pm">5.00-6.00 pm</option>
								<option value="6.00-7.00 pm">6.00-7.00 pm</option>
							</select>	
						</td>
					</tr>
					<tr>
						<td>Available Days<span style="color:red;">*</span></td>
						<td>
							<!-- <input type="text" id="availableDay" name="availableDay"/> -->
							<select id="availableDay" name="availableDay" class="fixSize" >
								<option value="Monday">Monday</option>
								<option value="Tuesday">Tuesday</option>
								<option value="Wednesday">Wednesday</option>
								<option value="Thursday">Thursday</option>
								<option value="Friday">Friday</option>
								<option value="Saturday">Saturday</option>
								<option value="Everyday">Everyday</option>
							</select>
						</td>
					</tr>
					<tr>
						<!-- <td><input type="button" value="Back" id="backBtn" onclick="backToLogin();" /> </td>
						<td><input type="submit" value="Submit" id="submit" name="submit" onclick="return validation();"/> </td>
						<td><input type="button" value="Reset" id="resetBtn" name="resetBtn" onclick="resetForm();"/> </td> -->
						
						<!-- <td><input type="button" value="Back" id="backBtn" onclick="backToLogin();" class="buttonStyle"/> </td>
						<td><input type="submit" value="Submit" id="submit" name="submit" onclick="return validation();" class="buttonStyle"/> </td>
						<td><input type="button" value="Reset" id="resetBtn" name="resetBtn" onclick="resetForm();" class="buttonStyle"/> </td> -->
					</tr>	
					
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