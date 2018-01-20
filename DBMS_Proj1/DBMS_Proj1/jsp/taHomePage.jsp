<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

	<% ResultSet rs = (ResultSet)request.getAttribute("rs");%>
	
	<table>
		<tr>
			<td>
				WELCOME <%=rs.getString("USER_ID") %>
				Profile-:<%=request.getAttribute("type").toString() %>
			</td>
		</tr>
		<tr>
			<td>
				<button name="viewProfile" value="viewProfile">
					<a href="<%=request.getContextPath()%>/TAController?action=viewProfile&USER_ID=<%=rs.getString("USER_ID")%>">"VIEW PROFILE"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="addStudent" value="Add Student">
					<a href="<%=request.getContextPath()%>/TAController?action=addStudentForm&USER_ID=<%=rs.getString("USER_ID")%>">"Enroll Student"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="dropStudent" value="Drop Student">
					<a href="<%=request.getContextPath()%>/TAController?action=dropStudentForm&USER_ID=<%=rs.getString("USER_ID")%>">"Drop Student"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="viewReport" value="View Report">
					<a onclick="$('#report').toggle();">View Report</a>
				</button>
			</td>
			<td><span id="report" style="display: none"> 
			<input id="courseid" type="text" name="courseID" placeholder="courseID" />
			<a href="<%=request.getContextPath()%>/ProfController?action=viewReport" onclick="this.href=this.href+'&c_id='+$('#courseid').val();">View</a>
			</span>
			</td>
		</tr>
	</table>

</body>
</html>