<%@page import="com.hms.bean.AppointmentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	
	<!-- <script src="js/jquery-1.9.1.js"></script> -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
  	<link rel="stylesheet" href="css/jquery-ui.css" type="text/css">
  	<link rel="stylesheet" href="css/style.css" type="text/css">
  
	<script type="text/javascript">
		var docDetailsarr = [];
		$(document).ready(function(){
			
			$('#appDate').datepicker({
				minDate:0,
				maxDate:1,
				dateFormat: 'yy-mm-dd',
				/* minDate: 0,
				beforeShowDay: function(date)
				{
					console.log(date.getDay());
					return [
				          	(date.getDay() == 2 //|| date.getDay() == 3 || date.getDay() == 4  
				          			//|| date.getDay() == 5 || date.getDay() == 6 || date.getDay() == 0
				          			), ""
		          		]; 
				} */
				
			});
			
			$("#specialization").change(function(){
				//alert(this.value);
				$.ajax({
					url:'getSpecialistDoctorList',
					method:'post',
					data:{specialization:this.value},
					datatype:'json',
					success: function ( res ) {

						var doctorStr="";
						for(var i=0; i<res.length; i++)
						{
							var data = res[i];
							
							var item = {};
							item['docId'] = data.id;
							item['docTime'] = data.data.split("@")[1];
							doctorStr += "<option value='"+data.id+"'>"+data.data.split("@")[0]+"</option>";
							docDetailsarr.push(item);
						}
						
						$("#doctorsList").append(doctorStr);
					}
				});
			});
			
			$("#doctorsList").change(function(){
				for(var i=0; i<docDetailsarr.length; i++)
				{
					if(this.value == docDetailsarr[i].docId)
					{
						var timeStr = docDetailsarr[i].docTime;
						var amOrpm = "";
						if(timeStr.length == 13)
							amOrpm = " "+timeStr.substring(11, 13);
						else
							amOrpm = " "+timeStr.substring(12, 14);
						var firstApp = timeStr.substring(0,6)+""+timeStr.substring(0,3)+"30"+amOrpm;
						var secondApp = timeStr.substring(0,3)+"30"+timeStr.substring(5,11)+amOrpm;
						var appointmentTime = "<option value='"+firstApp+"'>"+firstApp+"</option> <option value='"+secondApp+"'>"+secondApp+"</option>";
						$('#appTime').find('option:not(:first)').remove();
						$("#appTime").append(appointmentTime);
					}
					else if(this.value == "")
					{
						$('#appTime').find('option:not(:first)').remove();
						break;
					}
				}
			});
			
			$("#submit").click(function(){
				
				var flag=true;
				if($("#specialization").val() == "")
					flag=false;
				else if($("#doctorsList").val() == "")
					flag=false;
				else if($("#appDate").val() == "")
					flag=false;
				else if($("#appTime").val() == "")
					flag=false;
				
				if(!flag)
					alert("Please enter mandatory fields.");
				
				//var appDetails=[];
				var item = {};
				
				item['patientId']= $("#patientId").val();
				item['doctorId']= $("#doctorsList").val();
				item['appointmentDate']= $("#appDate").val();
				item['appointmentTime']= $("#appTime").val();
				//appDetails.push(item);
				//alert('<%=session.getAttribute("birthDate")%>');				
				if(flag)
				{
					$.ajax({
						url:'saveAppointment',
						method:'post',
						data:item,
						datatype:'json',
						success: function ( res ) {
							
							if(res.indexOf("fixed") > 0)
							{
								var birthDate = '<%=session.getAttribute("birthDate")%>';
								var gender = '<%=session.getAttribute("gender")%>';
								var pname = '<%=session.getAttribute("patientName")%>';
								var tableRow = "<tr><td>"+$("#doctorsList option:selected").text()+"</td><td>"+ pname +"</td><td>"+birthDate+"</td><td>"+gender+"</td><td>"+ 
								$("#appDate").val()+"</td><td>"+$("#appTime option:selected").text()+"</td></tr>";
								$("#patientDetails").append(tableRow);
							}
							$("#msg").html(res);
							
						}
					});
				}
			});
			
		});
	</script>
</head>
<body>
	<div style="padding: 25px 0px 0px 550px;">
		Patient Appointment Details
		<span style="padding: 0px 0px 0px 345px;"><a href="logout">Logout</a></span>
	</div>
	
	<div style="margin: 25px 200px;border: 1px solid black;">
		<table id="patientDetails">
		<tr>
			<th>Doctor Name</th>
			<th>Name</th>
			<th>Birth Date</th>
			<th>Gender</th>
			<!-- <th>Marital status</th> -->
			<th>App Date</th>
			<th>App Time</th>
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
	
	<div style="margin: 50px 460px;width: 350px;border: 0px solid;">
		<%-- Paiten Id: <%=session.getAttribute("patientId")%> --%>
		<div id="msg" style="color: red;"></div>
		<form action="">
			<table>
				<tr>
					<td>Specialization<span style="color:red;">*</span> </td>
					<td>
						<input type="hidden" id="patientId" name="patientId" value="<%=session.getAttribute("patientId")%>" />
						<select id="specialization" name="specialization" class="fixSize">
							<option>Select</option>
							<option value="Pediatrician">Pediatrician</option>
								<option value="Opthalmologist">Ophthalmologist</option>
								<option value="Dermatologist">Dermatologist</option>
								<option value="Cardiologist">Cardiologist</option>
								<option value="Psychiatrist">Psychiatrist</option>
								<option value="General Surgeon">General Surgeon</option>
								<option value="Dentist">Dentist</option>								
						</select>
					</td>					
				</tr>
				<tr>
					<td>Doctor Name<span style="color:red;">*</span></td>
					<td>
						<select id="doctorsList" name="doctorsList" class="fixSize">
							<option value="">Select</option>	
						</select>
					</td>					
				</tr>
				<tr>
					<td>Appointment Date<span style="color:red;">*</span></td>
					<td><input type="text" id="appDate" name="appDate" /> </td>
				</tr>
				<tr>
					<td>Appointment Time<span style="color:red;">*</span></td>
					<td>
						<select id="appTime" name="appTime" class="fixSize">
							<option value="">Select</option>
						</select>
					</td>					
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="Submit" id="submit" class="buttonStyle" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>