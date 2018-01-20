<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="dropStudent" action="<%=request.getContextPath()%>/<%=request.getAttribute("ControllerType")%>Controller?action=dropStudent" method="post" >
	<table>
		<tr>
			<td>
				Student ID
			</td>
			<td>
				<input id="studentID" name="studentID"  type="text" placeholder="studentID" />
			</td>
		</tr>
		<tr>
			<td>
				Course ID
			</td>
			<td>
				<input id="courseID" name="courseID"  type="text" placeholder="courseID" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Drop Student"/>
			</td>
		</tr>	
	</table>
</form>

</body>
</html>