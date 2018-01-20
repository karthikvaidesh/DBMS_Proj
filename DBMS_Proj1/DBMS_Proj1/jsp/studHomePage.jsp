<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% ResultSet rs = (ResultSet)request.getAttribute("rs");%>
	
	<table>
		<tr>
			<td>
				WELCOME <%=rs.getString("USER_ID") %>
				Profile-:<%=request.getAttribute("type").toString() %>
			</td>
		</tr>
		<tr>
			<td>
				<button name="profile" value="profile">
						<a href="<%=request.getContextPath()%>/StudController?action=viewProfile&USER_ID=<%=rs.getString("USER_ID")%>">View Profile</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="courses" value="courses">
						<a href="<%=request.getContextPath()%>/StudController?action=viewEnrolledCourses&USER_ID=<%=rs.getString("USER_ID")%>">Enrolled Courses</a>
				</button>
			</td>
		</tr>
		
	</table>

</body>
</html>