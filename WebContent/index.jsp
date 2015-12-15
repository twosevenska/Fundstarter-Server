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
    <link type="text/css" rel="stylesheet"  href="res/css/style.css">
 	
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Fundstarter</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <s:textfield key="user" class="form-control"/>
            </div>
            <div class="form-group">
              <s:password key="pass" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
	<div class="container">
		<div class="starter-template">
			<h1>Welcome To Struts 2!</h1>
			<p class="lead"><a href="<s:url action='hello'/>">Hello World</a></p>
		</div>
		<table id="grid-data" class="table table-condensed table-hover table-striped">
		    <thead>
		        <tr>
		            <th data-column-id="name" data-formatter="link">Name</th>
		            <th data-column-id="status">Status</th>
		            <th data-column-id="achieved">Achieved</th>
		            <th data-column-id="date" data-order="desc">Date</th>
		        </tr>
		    </thead>
		</table>
	</div>
	<!-- Scripts -->
	<!-- Sonic's the name, speed's my game! -->
	<!-- JQuery core JS -->
	<script src="res/jquery/jquery-1.11.3.min.js"></script>
	<!-- JQuery-Bootgrid core JS -->
	<script src="res/bootgrid/jquery.bootgrid.js"></script>
	<!-- Bootstrap core JS -->
 	<script src="res/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>