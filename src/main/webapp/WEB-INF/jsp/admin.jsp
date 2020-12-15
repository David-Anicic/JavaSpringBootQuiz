<% if (session.getAttribute("logedin")!=null && session.getAttribute("logedin").equals("yes")) {
		if (session.getAttribute("tof").equals("user"))
			response.sendRedirect("/login");
		}
	else
		response.sendRedirect("/login");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="controller.UserController" %>
<%@page import="model.User" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
	body {
		text-align: center;
	}
	table {
		 margin-left: auto;
    	 margin-right: auto;
		border-collapse: collapse;
	}

	table, th, td {
		border: 1px solid black;
		padding: 5px;
	}
</style>
<title>Admin page</title>
</head>
<body onload="loadQuestions();">
	<h1>Welcome to the Admin Page</h1>
	
	<h3>Add new question:</h3>
	<form method="post">
        Content : <input type="text" name="content" id="content" /></br>
        <input type="button" value="Add question" onclick="addQuestion()" />
    </form>
    <p id="msg"></p>
    <h3>All questions</h3>
	<div id='userTable7'></div><br>
	<h3>Add answers</h3>
	<div>
		<form action="question/addAnswers" method="POST" id="form1">
			<select name="questionID" id="selectTag"></select>
			<input type="number" name="numOfAnswers" id="noa" onchange="generateInputs()">
			<div id="answersDiv"></div>
			<input type="submit" value="Add Answers"/>
		</form>
	</div>
	<h3>Table of all registered users</h3>
	<div id='userTable1'></div><br>
	<h3>Table of all users that are correct answered all the questions.</h3>
	<div id='userTable2'></div><br>
	<h3>Table of all users that are answered all the questions.</h3>
	<div id='userTable3'></div><br>
	<h3>Table of all users sorted by performance.</h3>
	<div id='userTable4'></div><br>
	<h3>A list of questions sorted by a non-increasing percentage of correct answers.</h3>
	<div id='userTable5'></div><br>
	<h3>List of frequently asked incorrect questions and the answer itself.</h3>
	<div id='userTable6'></div><br>
	<button onClick="logout()">Logout</button>
	<script>
		function logout() {
			$.get('/logout', function(data, textStatus, jqXHR) {
				location.reload();
			});
		}
	
		function generateInputs() {
			$('#answersDiv').html("");

			var n = parseInt($('#noa').val());
			var i;
			for(i=0;i<n;i++) {
				$('#answersDiv').append("Answer: <input type='text' name='answers[]' /><input type='radio' name='correct' value='"+i+"'/>Correct<br>");
			}
		}
		
		$.get('/users/all', function(data, textStatus, jqXHR) {
			table = $('<table><tr><th>Name</th><th>Surname</th><th>Username</th><th>Date of last login</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['name']+"</td><td>"+data[i]['surname']+"</td><td>"+data[i]['username']+"</td><td>"+data[i]['date']+"</td></tr>");	
			}
			
			$('#userTable1').append(table);
		});
		
		$.get('/users/allCorrectAnswered', function(data, textStatus) {
			table = $('<table><tr><th>Name</th><th>Surname</th><th>Username</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['name']+"</td><td>"+data[i]['surname']+"</td><td>"+data[i]['username']+"</td></tr>");	
			}
			
			$('#userTable2').append(table);
		});
		
		$.get('/users/allAnswered', function(data, textStatus) {
			table = $('<table><tr><th>Name</th><th>Surname</th><th>Username</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['name']+"</td><td>"+data[i]['surname']+"</td><td>"+data[i]['username']+"</td></tr>");	
			}
			
			$('#userTable3').append(table);
		});
		
		$.get('/users/sortedUsersByPerformance', function(data, textStatus) {
			table = $('<table><tr><th>Name</th><th>Surname</th><th>Username</th><th>Points</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['name']+"</td><td>"+data[i]['surname']+"</td><td>"+data[i]['username']+"</td><td>"+data[i]['count']+"</td></tr>");	
			}
			
			$('#userTable4').append(table);
		});
		
		$.get('/question/percentagesOfTrueQuestions', function(data, textStatus) {
			table = $('<table><tr><th>Question</th><th>Percentage</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['content']+"</td><td>"+data[i]['percentage']+"%</td></tr>");	
			}
			
			$('#userTable5').append(table);
		});
		
		$.get('/question/percentagesOfFalseQuestions', function(data, textStatus) {
			table = $('<table><tr><th>Question</th><th>Answer</th><th>Percentage</th></tr></table>');
			
			for(i = 0; i < data.length; i++) {
				table.append("<tr><td>"+data[i]['content']+"</td><td>"+data[i]['contentAnswer']+"</td><td>"+data[i]['percentage']+"%</td></tr>");	
			}
			
			$('#userTable6').append(table);
		});
		
		function loadQuestions() {
		
			$('#selectTag').html("");
			
			$.get('/question/all', function(data, textStatus) {
				table = $('<table><tr><th>Question</th><th>Delete</th></tr></table>');
				
				for(i = 0; i < data.length; i++) {
					$('#selectTag').append("<option value='"+data[i]['questionID']+"'>"+data[i]['content']+"</option>");

					table.append("<tr><td>"+data[i]['content']+"</td><td><button onClick='deleteQuestion("+data[i]['questionID']+")'>Delete</button></td>");
				}
				
				$('#userTable7').html("");
				$('#userTable7').append(table);
			});
		}
		
		function addQuestion() {
			if($("#content").val() != "") {
				$.post("/question/addQuestion", { content : $("#content").val() }, function(data) {
					loadQuestions();
				});
			}
			else {
				$("#msg").val("You need to enter name of the question!");
			}
			
			$("#content").val("");
		}
		
		function deleteQuestion(questionID) {
			$.ajax({
			    url: 'question/deleteQuestion',
			    type: 'DELETE',
			    data: { 'questionID' : questionID },
			    success: function(result) {
			    	loadQuestions();
			    }
			});
		}
	</script>
</body>
</html>