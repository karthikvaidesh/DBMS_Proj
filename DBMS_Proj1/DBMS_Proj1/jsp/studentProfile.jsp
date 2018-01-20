<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String a[]=(String [])request.getAttribute("details"); 
if(a!=null){%>
<p>Student id:<%=a[0] %></p>
<p>Name:<%=a[1]+" "+a[2]%></p>
<%} %>



</body>
</html>