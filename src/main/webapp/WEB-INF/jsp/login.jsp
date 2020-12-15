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
<title>Login</title>
</head>
<body>
	<h3>Login to continue</h3>
    <font color="red">${errorMessage}</font>
    <form method="post">
        Username : <input type="text" name="username" /></br>
        Password : <input type="password" name="password" /> </br>
        <input type="submit" value="Login" />
    </form>
    <p>If you don't have an account yet, create one <a href="/register">here</a></p>
</body>
</html>