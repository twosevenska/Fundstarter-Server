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
	
	<title>Fundstarter - User Panel</title>
	
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
            <a href="<s:url action='user'/>" class="btn btn-success">User Panel</a>
            <a href="#" class="btn btn-danger">Logout</a>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    
    <div class="project-body">
		<div class="row project-title">
			<div class="col-md-8">
				<h1>Mr.Teapot</h1>
			</div>
		</div>
		
		<div class="row project-title">
			<div class="col-md-1">
				<div id="addFunds"><a data-toggle="modal" href="#add-Funds" class="btn btn-primary btn-large">Add Funds</a></div>
			</div>
			<div class="col-md-1">
				<a href="<s:url action='create-project'/>" class="btn btn-primary btn-large">Create Project</a>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-12">
			<p><b>My Rewards</b></p>
			<table id="my-rewards-table" class="projects-table"></table></div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-12">
			<p><b>My Projects</b></p>
			<table id="my-projects-table" class="projects-table"></table></div>
		</div>
	</div>
	
	<!-- Modal add-Funds -->
	<div class="modal fade" id="add-Funds" tabindex="-1" role="dialog" 
	     aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <!-- Modal Header -->
	            <div class="modal-header">
	                <button type="button" class="close" 
	                   data-dismiss="modal">
	                       <span aria-hidden="true">&times;</span>
	                       <span class="sr-only">Close</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">Add Funds</h4>
	            </div>
	            
	            <!-- Modal Body -->
	            <div class="modal-body">
	                
	                <form role="form">
	                  <div class="form-group">
	                      <input type="number" class="form-control"
	                      id="money-Ammount" placeholder="Enter ammount"/>
	                  </div>
	                  <button type="submit" class="btn btn-default">Submit</button>
	                </form>
	            </div>
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
 	<!-- Populate Grid -->
 	<script>
 	$('#my-projects-table').bootstrapTable({
 	    columns: [{
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
 	        name: 'Futurama',
 	       	status: 'Active',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }, {
 	        name: 'Firefly',
 	       	status: 'Canceled - But in our hearts',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }]
 	});
 	</script>
 	
 	 <script>
 	$('#my-rewards-table').bootstrapTable({
 	    columns: [{
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
 	        name: 'Futurama',
 	       	status: 'Active',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }, {
 	        name: 'Firefly',
 	       	status: 'Canceled - But in our hearts',
 	      	percentage: '99999%',
 	      	date: '31-02-2016',
 	      	link: 'Lorem' 
 	    }]
 	});
 	</script>
</body>
</html>