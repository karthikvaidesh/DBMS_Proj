<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<% HashMap<String,String>topics= (HashMap<String,String>)request.getAttribute("topics");%>
	<form name="newHW" action="<%=request.getContextPath()%>/ProfController?action=newHW" method="post">
		<table>
			<tr>
				<td>HW ID</td>
				<td><input id="hwID" name="hwID" type="text" placeholder="hwID" />
				</td>
			</tr>
			<tr>
				<td>HW Name</td>
				<td><input id="hwName" name="hwName" type="text"
					placeholder="hwName" /></td>
			</tr>
			<tr>
				<td>Course ID</td>
				<td><input id="courseID" name="courseID" type="text"
					placeholder="courseID" /></td>
			</tr>
			<tr>
				<td>Topic</td>
				<td><select name="topics">

						<%
							for (String keys : topics.keySet()) {
						%>

						<option value="<%=keys%>"><%=topics.get(keys)%></option>

						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Number of Retries</td>
				<td><input id="noOfRetries" name="noOfRetries" type="text"
					placeholder="noOfRetries" /></td>
			</tr>
			<tr>
				<td>Start Date</td>
				<td><input id="startDate" name="startDate" type="date"
					placeholder="StartDate" /></td>
			</tr>
			<tr>
				<td>End Date</td>
				<td><input id="endDate" name="endDate" type="date"
					placeholder="EndDate" /></td>
			</tr>
			<tr>
				<td>Points per Question</td>
				<td><input id="pointsPerQuestion" name="pointsPerQuestion"
					type="text" placeholder="pointsPerQuestion" /></td>
			</tr>
			<tr>
				<td>Penalty points</td>
				<td><input id="penaltyPoints" name="penaltyPoints" type="text"
					placeholder="penaltyPoints" /></td>
			</tr>
			<tr>
				<td><input type="radio" name="hwType" value="standard" checked="checked" />
					Standard</td>
				<td><input type="radio" name="hwType" value="adaptive" />
					Adaptive</td>
			</tr>
			<tr>
				<td><input type="submit" value="Add Questions" /></td>
			</tr>
		</table>
	</form>

</body>
</html>