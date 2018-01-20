<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
Your Courses - 
<table>
	<% ResultSet rs = (ResultSet)request.getAttribute("rs");
	
	   while(rs.next()) {
	%>
		<tr>
			<td>
				<%=rs.getString("COURSE_ID") %>
			</td>
			<td>
				<%=rs.getString("COURSE_NAME") %>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/ProfController?action=viewHomeworks&c_id=<%=rs.getString("course_id")%>">View Homeworks</a>
			</td>
		</tr>
	<%
	   }
	%>


</body>
</html>