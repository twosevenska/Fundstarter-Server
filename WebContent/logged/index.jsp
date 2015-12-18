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
    <link rel="stylesheet" href="res/bootstrap-table/bootstrap-table.css">
</head>
<body>

	<%@include file="header.jsp" %>
    
    <div class="project-body">
		<div class="row project-title">
			<div class="col-md-8">
				<h1>Projects</h1>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-12">
			<table id="table" class="projects-table"></table></div>
		</div>	
		<center><a href="<s:url action='hello'/>">Go to sample project</a></center>
		<center><a href="<s:url action='user'/>">Go to sample user panel</a></center>
	</div>
	
	<!-- Scripts -->
	<!-- Sonic's the name, speed's my game! -->
	<!-- JQuery core JS -->
	<script src="res/jquery/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap core JS -->
 	<script src="res/bootstrap/js/bootstrap.min.js"></script>
 	<!-- Bootstrap-table core JS -->
 	<script src="res/bootstrap-table/bootstrap-table.js"></script>
 	<!-- Populate Grid -->
 	<script>
 	$('#table').bootstrapTable({
 	    columns: [{
 	        field: 'id',
 	        title: 'id'
 	    },{
 	        field: 'name',
 	        title: 'Project'
 	    }, {
 	        field: 'status',
 	        title: 'Current Status'
 	    }, {
 	        field: 'percentage',
 	        title: 'Goal'
 	    },{
 	        field: 'date',
 	        title: 'Final Date'
 	    },{
 	        field: 'link',
 	        title: 'Link'
 	    }],
 	    data: [{
 	    	id: '1',
 	        name: 'Futurama',
 	       	status: 'Active',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }, {
 	    	id: '1',
 	        name: 'Firefly',
 	       	status: 'Canceled - But in our hearts',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }]
 	});
 	$('#table').bootstrapTable('hideColumn', 'id');
 	
 	$('#table').on('click-row.bs.table', function (e, row, $element) {
 		window.location = 'hello';
    });
 	</script>
</body>
</html>