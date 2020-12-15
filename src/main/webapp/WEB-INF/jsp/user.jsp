<% if (session.getAttribute("logedin")!=null && session.getAttribute("logedin").equals("yes")) {
		if (session.getAttribute("tof").equals("admin"))
			response.sendRedirect("/login");
		}
	else
		response.sendRedirect("/login");
%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta charset="ISO-8859-1">
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
<title>User page</title>
<script type="text/javascript">

		 var questionsids = [];
		 var currentQuestion = -1;
		 var numberOfQuestions;
		 var idofuser;
		 var questonsanswered = 0;
		 
		 function logout() {
			$.get('/logout', function(data, textStatus, jqXHR) {
				location.reload();
			});
		}
		 
		 function init() {
			$.get('/question/all', function(data, textStatus) {
				
				numberOfQuestions = data.length;
				$('#totalQuestions').html("");
				$('#totalQuestions').append("<span>"+numberOfQuestions+"</span>");
				
				for(i=0;i<data.length;i++)
					window.questionsids.push(data[i]['questionID']);
				skipQuestion();
			});
		}

		function nextQuestion() {
			valueOfAnswer = $('#selectAnswer').val();
			idofuser = $('#userid').val();
			$.post("/question/submitAnswer", { questionID : questionsids[currentQuestion], answer : valueOfAnswer, userid : idofuser}, function(data) {
				skipQuestion();
				
				questonsanswered += 1;
				
				if(questonsanswered==numberOfQuestions)
					showTable();
			});
		}
		
		function addNullAnswer() {
			valueOfAnswer = null;
			idofuser = $('#userid').val();
			$.post("/question/submitAnswer", { questionID : questionsids[currentQuestion], answer : valueOfAnswer, userid : idofuser}, function(data) {
				//skipQuestion();
				
				//questonsanswered += 1;
				
				//if(questonsanswered==numberOfQuestions)
				//	showTable();
			});
		}
		
		function skipQuestion() {
			currentQuestion += 1;
			if( currentQuestion < numberOfQuestions) {
				
				$.get('/question/getQuestionAnswers?questionid='+questionsids[currentQuestion], function(data, textStatus) {
					
					$('#currentQuestion').html("");
					pom = currentQuestion+1;
					$('#currentQuestion').append(pom);
					
					$('#question').html("");
					$('#question').append("<span>"+data[0]['questionContent']+"</span>");
					
					$('#selectAnswer').html("");
					for(i=0;i<data.length;i++)
						$('#selectAnswer').append("<option value='"+data[i]['answerContent']+"'>"+data[i]['answerContent']+"</option>");
				});
			}
			else {
				$('#end').html("");
				$('#end').append("<span><b>You answered all the questions. Reload to answer again.</b></span>")
			}
		}
		
		function quite() {
			location.reload();
			return false;
		}
		
		function showTable() {
			$.get('/users/sortedUsersByPerformance', function(data, textStatus) {
				table = $('<table><tr><th>Name</th><th>Surname</th><th>Username</th><th>Points</th></tr></table>');
				
				// dodati za prvih 5 i za pobednika
				n = data.length;
				if(data.length > 5)
					n = data.length;
				for(i = 0; i < n; i++) {
					points = data[i]['count'];
					table.append("<tr><td>"+data[i]['name']+"</td><td>"+data[i]['surname']+"</td><td>"+data[i]['username']+"</td><td>"+points+"</td></tr>");	
				}
				
				$('#tableofpoints').html("");
				$('#tableofpoints').append(table);
				
				if(data[0]['username']==$('#username').val()) {
					$('#winner').append("<b>Congratulations "+data[0]['username']+" you have highest score.</b>")
				}
			});
		}
	</script>
</head>
<body onload="init()">
	<input id="userid" value="<%= session.getAttribute("userid")%>" style="display: none"/>
	<input id="username" value="<%= session.getAttribute("username")%>" style="display: none"/>
	<p>Welcome to the User page.</p>
	<p id="question"></p><span id="currentQuestion">1</span>/<span id="totalQuestions">6</span></span>
	<p>Odaberi tacan odgovor:</p>
	<select name="answer" id="selectAnswer"></select><br>
	<button onClick="nextQuestion()">Submit and next</button>
	<button onClick="skipQuestion();addNullAnswer();">Skip</button>
	<button onClick="quite()">Quite</button>
	<p id='end'></p>
	<div id="tableofpoints"></div>
	<p id="winner"></p>
	<button onClick="logout()">Logout</button>
</body>
</html>