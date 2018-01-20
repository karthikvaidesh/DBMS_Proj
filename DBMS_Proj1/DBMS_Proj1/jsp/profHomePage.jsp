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
	<%
		ResultSet rs = (ResultSet) request.getAttribute("rs");
	%>

	<table>
		<tr>
			<td>WELCOME <%=rs.getString("USER_ID")%>
			Profile-:<%=request.getAttribute("type").toString() %>
			</td>
		</tr>
		<tr>
			<td>
				<button name="viewProfile" value="viewProfile">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=viewProfile&USER_ID=<%=rs.getString("USER_ID")%>">"VIEW
						PROFILE"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="addCourse" value="Add Course">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=addCourseForm&USER_ID=<%=rs.getString("USER_ID")%>">"Add
						Course"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="addStudent" value="Add Student">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=addStudentForm&USER_ID=<%=rs.getString("USER_ID")%>">"Enroll
						Student"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="dropStudent" value="Drop Student">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=dropStudentForm&USER_ID=<%=rs.getString("USER_ID")%>">"Drop
						Student"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="addTA" value="Add TA">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=addTAForm">"Add
						TA"</a>
				</button>
			</td>
		</tr>
		<tr>
			<td>
				<button name="addQuestion" value="Add Question">
					<a
						href="<%=request.getContextPath()%>/ProfController?action=addQuestionForm">"Add
						Question"</a>
				</button>
			</td>
		</tr>

		<tr>
			<td>
				<button name="viewReport" value="View Report">
					<a onclick="$('#report').toggle();">View Report</a>
				</button>
			</td>
			<td><span id="report" style="display: none"> <input
					id="courseid" type="text" name="courseID" placeholder="courseID" />
					<a
					href="<%=request.getContextPath()%>/ProfController?action=viewReport" onclick="this.href=this.href+'&c_id='+$('#courseid').val();">View</a>
			</span></td>
		</tr>
		<tr>
			<td>
				<button name="createHW" value="Create HW">
					<a id="test"
						href="<%=request.getContextPath()%>/ProfController?action=createHWForm"
						onclick="this.href=this.href+'&hw='+$('input[name=\'hw\']:checked').val()+'&courseID='+$('#c_id').val();">"Create
						Homework"</a>

				</button>
			</td>
			<td><input type="radio" name="hw" value="new" checked="checked"
				onclick="$('#courseID').hide();" /> NEW</td>
			<td><input type="radio" name="hw" value="existing"
				onclick="$('#courseID').show();" /> EXISTING</td>
		</tr>
	</table>
	<div id="courseID" style="display: none;">
		<input id="c_id" type="text" name="courseID" placeholder="courseID" />
	</div>

</body>


</html>