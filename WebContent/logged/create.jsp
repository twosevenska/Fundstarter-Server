<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Fundstarter - Create a Project</title>
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="res/bootstrap/css/bootstrap.min.css">
 	 <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet"  href="res/css/style.css">
    <link rel="stylesheet" href="res/bootstrap-table/bootstrap-table.css">
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
          <a class="navbar-brand" href="<s:url action='index'/>">Fundstarter</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <form class="navbar-form navbar-right">
            <a href="#" class="btn btn-success">User Panel</a>
            <a href="#" class="btn btn-danger">Logout</a>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    
    <div class="project-body">
		<div class="row project-title">
			<div class="col-md-8">
				<h1>Create Your Project!</h1>
			</div>
		</div>
		
		<div class="row project-title">
			<div class="col-md-12">
				<form class="form-horizontal" role="form">
				  <div class="form-group">
				    <div class="col-sm-10">
				    	<p>Project Name:</p>
				      	<input type="text" class="form-control" id="name" placeholder="Choose a catchy name">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10"> 
				    	<p>Short Description:</p>
				      <input type="text" class="form-control" id="description" placeholder="Explain your idea">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10"> 
				    	<p>End Date:</p>
				      <input type="date" class="form-control" id="date">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10"> 
				    	<p>Minimum amount:</p>
				      <input type="number" class="form-control" id="amount" placeholder="Tell us how much you need">
				    </div>
				  </div>
				  <div class="form-group"> 
				    <div class="col-sm-10">
				      <button href="<s:url action='reward'/>" type="submit" class="btn btn-default">Submit</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	</div>
	

	<!-- Scripts -->
	<!-- Sonic's the name, speed's my game! -->
	<!-- JQuery core JS -->
	<script src="res/jquery/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap core JS -->
 	<script src="res/bootstrap/js/bootstrap.min.js"></script>
 	<!-- Bootstrap-table core JS -->
 	<script src="res/bootstrap-table/bootstrap-table.js"></script>
</body>
</html>