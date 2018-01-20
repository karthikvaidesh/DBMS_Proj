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
			<th>Question id</th>
			<th></th>
			<th>Question Text</th>
			<th></th>
			<th>Marked Answer</th>
			<th></th>
			<th>Is correct</th>
			<th></th>
		    <th>Detailed Answer</th>
		    <th></th>
		    <th>Points Scored</th>
		</tr>
		<%
			ResultSet rs = (ResultSet) request.getAttribute("rs");
			if (rs != null) {
				while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("q_id")%></td>
			<td></td>
			<td><%=rs.getString("Q_TEXT")%></td>
				<td></td>
			<td><%=rs.getString(5)%></td>
				<td></td>
			<td><%=rs.getString(4)%></td>
				<td></td>
			<td><%=rs.getString(6)%></td>
				<td></td>
			<td><%=rs.getString(7)%></td>

		</tr>
		<%
			}

			}
		%>

	</table>


</body>
</html>