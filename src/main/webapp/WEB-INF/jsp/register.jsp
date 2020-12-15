<% if (session.getAttribute("logedin")!=null && session.getAttribute("logedin").equals("yes"))
		if (session.getAttribute("tof").equals("admin"))
			response.sendRedirect("/admin");
		else if (session.getAttribute("tof").equals("user"))
			response.sendRedirect("/user");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register page</title>
</head>
<body>
<body>
	<h3>Register to join the quiz.</h3>
    <font color="red">${errorMessage}</font>
    <form method="post">
        Name: <input type="text" name="name" /><br>
        Surname: <input type="text" name="surname" /><br>
        Username: <input type="text" name="username" /><br>
        Password: <input type="password" name="password" /><br>
        Email: <input type="text" name="email" /><br>
        <input type="submit" value="Register" />
    </form>
</body>
</body>
</html>