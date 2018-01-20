<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%HashMap<String,ArrayList> questions=(HashMap<String,ArrayList>)request.getAttribute("questions") ;

    for(String key:questions.keySet())
    { %>
	
		<input type="radio" name="qset" value="<%=key%>" onclick="$('#div_<%=key%>').show();"> <%=key%>
		<div id="div_<%=key%>" style="display:hidden;">
			<% ArrayList<String> ques =  questions.get(key);
			   for(String s : ques) {
			%>
				<p><%=s %> </p>
			 <%} %>
		</div> 
	<%}%>

</body>
</html>