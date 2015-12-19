<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Fundstarter</title>
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="res/bootstrap/css/bootstrap.min.css">
 	 <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet"  href="res/css/loginStyle.css">
    <link rel="stylesheet" href="res/bootstrap-table/bootstrap-table.css">
</head>
<body>
	<div class="row">
		<div class="col-md-4"></div>
	    <div class="container col-md-4">
	      <s:form action="register" class="form-signin">
	        <h2 class="form-signin-heading">Welcome!</h2>
	        <h2 class="form-signin-heading">Obtain your privilege</h2>
			<s:textfield name="registerStore.username" class="form-control" placeholder="username" required="true"/>
	        <s:password name="registerStore.password" class="form-control" placeholder="Password" required="true"/>
	        <s:submit class="btn btn-lg btn-primary btn-block" label="Register" />
	      </s:form>
	    </div>
	    <div class="col-md-4"></div>
	</div>
	<!-- Scripts -->
	<!-- Sonic's the name, speed's my game! -->
	<!-- JQuery core JS -->
	<script src="res/jquery/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap core JS -->
 	<script src="res/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>