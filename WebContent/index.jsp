<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Basic Struts 2 Application - Welcome</title>
		<link rel="stylesheet" href="res/css/bootstrap.min.css">
 		<script src="res/js/jquery.min.js"></script>
  		<script src="res/js/bootstrap.min.js"></script>
	</head>
	<body>
		<h1>Welcome To Struts 2!</h1>
		<p><a href="<s:url action='hello'/>">Hello World</a></p>
	</body>
</html>