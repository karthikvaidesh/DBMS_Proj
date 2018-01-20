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
			<th>Homework id</th>
			<th>Homework Name</th>
			<th>Attempt number</th>
		    <th>Marks obtained</th>
		</tr>
		<%
		    String user_id=request.getAttribute("u_id").toString();
			ResultSet rs = (ResultSet) request.getAttribute("rs");
			if (rs != null) {
				while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("hw_id")%></td>
			<td><%=rs.getString("hw_name")%></td>
			<td><%=rs.getString("attempt_no")%></td>
			<td><%=rs.getString(4)%></td>
			<td><a href="<%=request.getContextPath()%>/StudController?action=viewDetailedReport&u_id=<%=user_id%>&h_id=<%=rs.getString("hw_id")%>&a_no=<%=rs.getString("attempt_no")%>">View Detailed Report</a></td>

		</tr>
		<%
			}

			}
		%>

	</table>




</body>
</html>