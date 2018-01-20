<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>User_id</th><th>First Name</th><th>Last Name</th>
</tr>
<%ResultSet rs=(ResultSet)request.getAttribute("rs");
if(rs!=null){
	while(rs.next()){
		%>
	<tr>
	<td><%=rs.getString("user_id")%></td><td><%=rs.getString("f_name") %></td><td><%=rs.getString("l_name") %></td>
	<td><a href="<%=request.getContextPath()%>/ProfController?action=viewStudentReport&u_id=<%=rs.getString("user_id")%>&c_id=<%=rs.getString("course_id")%>">View Report</a></td>
	</tr>	
<% 	
	}
	
}

%>

</table>
</body>
</html>