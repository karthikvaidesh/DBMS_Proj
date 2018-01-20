<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Enrolled Courses</p>
	<table>
		<tr>
			<th>Course ID</th>
			<th>Course Name</th>
		</tr>
		<%
			ResultSet rs = (ResultSet) request.getAttribute("rs");
			if (rs != null) {
				while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("course_id")%></td>
			<td><%=rs.getString("course_name")%></td>
			<td>
				<button name="pendingHW" value="pendingHW">
					<a
						href="<%=request.getContextPath()%>/StudController?action=pendingHW&USER_ID=<%=rs.getString("USER_ID")%>&c_id=<%=rs.getString("course_id")%>">Pending Homeworks</a>
				</button>
			</td>
			<td>
				<button name="pastAttempts" value="pastAttempts">
					<a
						href="<%=request.getContextPath()%>/StudController?action=pastAttempts&user_id=<%=rs.getString("USER_ID")%>&c_id=<%=rs.getString("course_id")%>">Past
						Attempts</a>
				</button>
			</td>
		</tr>
		<%
			}

			}
		%>
	</table>
</body>
</html>