<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

    <%  response.setContentType("text/html");
PrintWriter out1 = response.getWriter();%>
<%String hiddenArr[]=((String[])request.getAttribute("newHWParams")); 
	   out1.println(hiddenArr[2]);
	%>
	<form name="selectQuestion" action="<%=request.getContextPath()%>/ProfController?action=addQuestionSet" method="post">
		
		<% for(int i = 0; i < 10; i++) {
		%>
			<input type="hidden" name="hiddenParams" value=<%=hiddenArr[i] %>>
		<% } %>
		
	
		<% HashMap<String,String>qList= (HashMap<String,String>)request.getAttribute("qList");%>
		<%
			for (String keys : qList.keySet()) {
		%>
				<tr>
					<td>
						<input type="checkbox" name="question" value="<%=keys%>"><%=qList.get(keys) %></option>
					</td>
				</tr>	
		<%
			}
		%>
		<input type="submit">
	</form>
</body>
</html>