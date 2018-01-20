<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% String user_id = (String)request.getAttribute("user_id");
	
%>
<form name="createNewCourse" action="<%=request.getContextPath()%>/ProfController?action=createCourse" method="post" autocomplete="on">
<h4>HOME PAGE</h4>
		<table>
			<tr>
				<td >Course ID</td> 
				<td><input id="courseid" name="courseid"  type="text" placeholder="courseid" /></td> 
				
			</tr>
			<tr>
				<td >Course Name</td> 
				<td><input id="coursename" name="coursename"  type="text" placeholder="coursename" /></td> 
				
			</tr>
			<tr>
				<td >Start Date</td> 
				<td><input id="startdate" name="startdate"  type="date" placeholder="startdate" /></td> 
				
			</tr>
			<tr>
				<td >End Date</td> 
				<td><input id="enddate" name="enddate"  type="date" placeholder="enddate" /></td> 
				
			</tr>
			<tr>
				<td >Topics(comma separated)</td> 
				<td><textarea rows="4" cols="50" id="topics" name="topics"></textarea></td> 
				
			</tr>
			<tr>
				<td >TA's(comma separated) (max 3)</td> 
				<td><textarea rows="4" cols="50" id="tas" name="tas"></textarea></td> 		
			</tr>
			<tr>
				<td >Maximum number of students</td> 
				<td><input id="maxStudents" name="maxStudents"  type="text" placeholder="maxStudents" /></td>  
				
			</tr>
			<input type="hidden" name="userid" value="<%=user_id%>"/>
			
			<tr>
				<td><input type="submit" value="Create Course"/>
				</td>
			</tr>
		</table>
</form>
	  
	

</body>
</html>