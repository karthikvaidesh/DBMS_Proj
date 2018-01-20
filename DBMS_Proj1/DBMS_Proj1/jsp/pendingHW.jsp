<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>HW ID</th>
			<th>Name</th>
			<th>StartDate</th>
			<th>EndDate</th>
			<th>No. of Retries</th>
			<th>Penalty per Question</th>
			<th>Points per Question</th>
			<th>No. of questions</th>
			<th>HW_TYPE</th>
		</tr>
		<%  String user_id=(String)request.getAttribute("user_id");
			ResultSet rs = (ResultSet) request.getAttribute("rs");
            if(rs!=null){
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("HW_ID")%></td>
			<td><%=rs.getString("HW_NAME")%></td>
			<td><%=rs.getDate("HW_STARTDATETIME")%></td>
			<td><%=rs.getDate("HW_ENDDATETIME")%></td>
			<td><%=rs.getInt("NO_OF_RETRIES")%></td>
			<td><%=rs.getFloat("PENALTY_PER_QUESTION")%></td>
			<td><%=rs.getInt("POINTS_PER_QUESTION")%></td>
			<td><%=rs.getInt("NO_OF_QUESTIONS")%></td>
			<td><%=rs.getString("HW_TYPE")%></td>
			<td>
				<a href="<%=request.getContextPath()%>/StudController?action=attemptHW&hw_ID=<%=rs.getString("HW_ID")%>&hw_type=<%=rs.getString("HW_TYPE")%>&user_id=<%=user_id%>">Attempt
					Now</a>
			</td>
		</tr>




		<%
			}
            }
		%>

	</table>


</body>
</html>