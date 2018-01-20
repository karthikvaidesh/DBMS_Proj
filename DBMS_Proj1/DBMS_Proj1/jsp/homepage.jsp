<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="user" action="<%=request.getContextPath()%>/UserController?action=login" method="post" >
					<h4>USER LOGIN</h4>
					<table>
						<tr>
							<td >Username <sup style="color: red">*</sup>&nbsp</td> 
							<td><input id="username" name="username"  type="text" placeholder="username" /></td> 
							
						</tr>
						<tr>
							<td>password <sup style="color: red">*</sup>&nbsp</td> 
							<td><input id="password" name="password"  type="password" placeholder="password" /></td> 					
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
						</tr>
						<tr>
							<td><input type="submit" value="Login"/>
							</td>
						</tr>
					</table>
					
					<p>
						
				   </p>
				   <p align="right" style="color: red">* - required field</p>
					
</form>
</body>
</html>