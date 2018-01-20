<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Integer count = 0;
		if(request.getAttribute("qCount") != null) 
			count = Integer.parseInt(request.getAttribute("qCount").toString());
		
		HashMap<String, ArrayList> qmap = (HashMap) request.getAttribute("qmap");
		String type = (String) request.getAttribute("type");
		String user_id = (String) request.getAttribute("user_id");%>
    <form name="selectQuestion" action="<%=request.getContextPath()%>/StudController?action=submitExam&user_id=<%=user_id%>&hwID=<%=request.getAttribute("hw_id")%>&qsetID=<%=request.getAttribute("qset_id")%>&from=adaptive&diff=<%=request.getAttribute("diff")%>&count=<%=count %>" method="post">
    
    <% 		if (type.equals("std")) {
			for (Map.Entry mp : qmap.entrySet()) {
	%>
	<table>
		<tr>
			<td><p><%=((ArrayList) mp.getValue()).get(0)%></p></td>
		</tr>

		<%
		HashMap<String, ArrayList> answers = (HashMap) ((ArrayList) mp.getValue()).get(4);
				//System.out.println(outerMap);

				for (Map.Entry mp1 : answers.entrySet()) {
	%>
		<tr>
			<td><input type="radio" name="option_<%=mp.getKey()%>"
				value="<%=((ArrayList) mp1.getValue()).get(0)%>@<%=(mp1.getKey())%>@<%=((ArrayList) mp.getValue()).get(3)%>"><%=((ArrayList) mp1.getValue()).get(0)%>
			</td>
		</tr>
		<%
		}
			}
	%>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>

	<%
		}
		else{
	%>

	<%for (Map.Entry mp : qmap.entrySet()) { %>
	<table>
		<tr>
			<td><p><%=((ArrayList) mp.getValue()).get(0)%></p></td>
		</tr>

		<%
		HashMap<String, ArrayList> answers = (HashMap) ((ArrayList) mp.getValue()).get(4);
				//System.out.println(outerMap);

				for (Map.Entry mp1 : answers.entrySet()) {
	%>
		<tr>
			<td><input type="radio" name="option_<%=mp.getKey()%>"
				value="<%=((ArrayList) mp1.getValue()).get(0)%>@<%=(mp1.getKey())%>@<%=((ArrayList) mp.getValue()).get(3)%>"><%=((ArrayList) mp1.getValue()).get(0)%>
			</td>
		</tr>
		<%
		}
			}
	%>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>

	<%} %>
	</form>

</body>
</html>