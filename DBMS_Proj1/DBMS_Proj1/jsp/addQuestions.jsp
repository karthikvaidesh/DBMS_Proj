<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% HashMap<String,String>topics= (HashMap<String,String>)request.getAttribute("topics");%>
<form name="addQuestion" action="<%=request.getContextPath()%>/<%=request.getAttribute("ControllerType") %>Controller?action=addQuestion" method="post" >
	<table>
	<tr>
			<td>
				Question ID
			</td>
			<td>
				<input id="qid" name="qid"  type="text" placeholder="qid" />
			</td>
		</tr>
		<tr>
			<td>
				Question Type
			</td>
			<td>
				<input id="standard" name="qtype"  type="radio" value="standard" onclick="$('#parameters').hide();"/>Standard
			</td>
			<td>
				<input id="parameterized" name="qtype"  type="radio" value="parameterized" onclick="$('#parameters').show();"/>Parameterized
			</td>
		</tr>
		
		
	
		<tr>
			<td>
				Question Text
			</td>
			<td>
				<input id="qtext" name="qtext"  type="text" placeholder="qtext" />
			</td>
		</tr>
		</table>
		<div id="parameters" style="display:none;">

		
		<table>
		<tr>
		
		<td>
		    Parameter Set 1
		    
		</td>
				</tr>
		<tr>
		<td>
				<input id="parameter1" name="parameter1v1"  type="text" placeholder="parameter1" />
			</td>
		
			
			<td>
				<input id="parameter2" name="parameter2v1"  type="text" placeholder="parameter2" />
			</td>
			<td>
				<input id="parameter3" name="parameter3v1"  type="text" placeholder="parameter3" />
			</td>
			<td>
				<input id="parameter4" name="parameter4v1"  type="text" placeholder="parameter4" />
			</td>
			<td>
				<input id="parameter5" name="parameter5v1"  type="text" placeholder="parameter5" />
			</td>
			</tr>
			
			<tr>
		
		<td>
		    Parameter Set 2
		    
		</td>
		
		</tr>
		<tr>
		<td>
				<input id="parameter1" name="parameter1v2"  type="text" placeholder="parameter1" />
			</td>
		
			<td>
				<input id="parameter2" name="parameter2v2"  type="text" placeholder="parameter2" />
			</td>
			<td>
				<input id="parameter3" name="parameter3v2"  type="text" placeholder="parameter3" />
			</td>
			<td>
				<input id="parameter4" name="parameter4v2"  type="text" placeholder="parameter4" />
			</td>
			<td>
				<input id="parameter5" name="parameter5v2"  type="text" placeholder="parameter5" />
			</td>
			</tr>
			</table>
		
		</div>
		
		<table>
		<tr>
			<td>
			  Topic
			</td>
			<td>
			<select name="topics">
			
			<%
                for(String keys:topics.keySet())
                {
			%>
				
				<option value="<%=keys%>"><%=topics.get(keys)%></option>
				
			<%} %>
			</select>
			</td>
		</tr>
		<tr>
			<td>
			  Difficulty Level(1 to 5)
			</td>
			<td>
				<input id="diff" name="diff"  type="text"/>
			</td>
		</tr>
		<tr>
			<td>
			 Hint
			</td>
			<td>
				<input id="hint" name="hint"  type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				Option 1
			</td>
			<td>
				<input id="ansid1" name="ansid1"  type="text" placeholder="ansid1" />
				
			</td>
			
			
			
			<td>
				<input id="anstext1" name="anstext1"  type="text" placeholder="anstext" />
			</td>
			
			<td>
			<input id="iscorrectans1" name="iscorrectans1"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
			</tr>
			<tr>
			<td>
				Option 2
			</td>
			<td>
				<input id="ansid2" name="ansid2"  type="text" placeholder="ansid2" />
			</td>
			
			
			<td>
				<input id="anstext2" name="anstext2"  type="text" placeholder="anstext" />
			</td>
			
			<td>
			<input id="iscorrectans2" name="iscorrectans2"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
		</tr>
		<tr>
			<td>
				Option 3
			</td>
			<td>
				<input id="ansid3" name="ansid3"  type="text" placeholder="ansid3" />
			</td>
			<td>
				<input id="anstext3" name="anstext3"  type="text" placeholder="anstext" />
			</td>
			
			<td>
			<input id="iscorrectans3" name="iscorrectans3"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
		</tr>
		<tr>
			<td>
				Option 4
			</td>
			<td>
				<input id="ansid4" name="ansid4"  type="text" placeholder="ansid4" />
			</td>
			<td>
				<input id="anstext4" name="anstext4"  type="text" placeholder="anstext" />
			</td>
			
			<td>
			<input id="iscorrectans4" name="iscorrectans4"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
		</tr>
		<tr>
			<td>
				Option 5
			</td>
			<td>
				<input id="ansid5" name="ansid5"  type="text" placeholder="ansid5" />
			</td>
			
			<td>
				<input id="anstext5" name="anstext5"  type="text" placeholder="anstext" />
			</td>
			
			<td>
			<input id="iscorrectans5" name="iscorrectans5"  type="text" placeholder="0-incorrect,1-correct" />
			</td5>
		</tr>
		
		<tr>
			<td>
				Option 6
			</td>
			<td>
				<input id="ansid6" name="ansid6"  type="text" placeholder="ansid6" />
			</td>
			<td>
				<input id="anstext6" name="anstext6"  type="text" placeholder="anstext" />
			</td>
			<td>
			<input id="iscorrectans6" name="iscorrectans6"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
			
		</tr>
		<tr>
			<td>
				Option 7
			</td>
			<td>
				<input id="ansid7" name="ansid7"  type="text" placeholder="ansid7" />
			</td>
			<td>
				<input id="anstext7" name="anstext7"  type="text" placeholder="anstext" />
			</td>
			<td>
			<input id="iscorrectans7" name="iscorrectans7"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
			
		</tr>
		<tr>
			<td>
				Option 8
			</td>
			<td>
				<input id="ansid8" name="ansid8"  type="text" placeholder="ansid8" />
			</td>
			<td>
				<input id="anstext8" name="anstext8"  type="text" placeholder="anstext" />
			</td>
			<td>
			<input id="iscorrectans8" name="iscorrectans8"  type="text" placeholder="0-incorrect,1-correct" />
			</td>
			
		</tr>
		
		<tr>
			<td>
				<input type="submit" value="Add Question"/>
			</td>
		</tr>	
	</table>
	
</form>

</body>


</html>