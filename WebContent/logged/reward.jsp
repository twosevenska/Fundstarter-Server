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
	
	<title>Fundstarter - Add a Reward</title>
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="res/bootstrap/css/bootstrap.min.css">
 	 <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet"  href="res/css/style.css">
    <link rel="stylesheet" href="res/bootstrap-table/bootstrap-table.css">
</head>
<body>
	<%@include file="header.jsp" %>
    
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
				    	<p>Short Description:</p>
				      <input type="text" class="form-control" id="description" placeholder="What will the pledger receive?">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10"> 
				    	<p>Minimum amount:</p>
				      <input type="number" class="form-control" id="amount" placeholder="Tell us the minimum ammount to pledge in this tier.">
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