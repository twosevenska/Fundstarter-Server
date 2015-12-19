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
    
    <%@include file="header.jsp" %>
    
    <div class="project-body">
		<div class="row project-title">
			<div class="col-md-8">
				<h1><s:property value="#session.userName" /></h1>
			</div>
		</div>
		
		<div class="row project-title">
			<div class="col-md-1">
				<a href="<s:url action='createProjectPage'/>" class="btn btn-primary btn-large">Create Project</a>
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
 	var websocket = null;
 	var $table = $('#my-projects-table');
 	var selectedServer = "<s:property value='%{#session.userId}'/>";
 	 	
 	window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
 	  	connect('ws://' + window.location.host + '/FundstarterServer/ws');
 	}

 	function connect(host) { // connect to the host websocket
 	    if ('WebSocket' in window)
 	    	websocket = new WebSocket(host);
 	    else if ('MozWebSocket' in window)
 	       	websocket = new MozWebSocket(host);
 	    else {
 	            writeToHistory('Get a real browser which supports WebSocket.');
 	            return;
 	    }

 	        websocket.onopen    = onOpen; // set the event listeners below
 	        websocket.onmessage = onMessage;
 	        
 	}
 	    
 	    function onOpen(event) {
 	    	websocket.send("userId-"+selectedServer); 
 	    }
 	    
 	    function onMessage(message) { // print the received message
 	        writeToHistory(message.data);
 	        console.log(message.data);
 	    }
 	    
 	    function writeToHistory(text) {
 	    	var jsonstuff = JSON.parse(text);
 	    	$table.bootstrapTable('destroy');
 	     	$table.bootstrapTable(jsonstuff);
 	     	$table.bootstrapTable('hideColumn', 'projId');
 	     	
 	     	$table.on('click-row.bs.table', function (e, row, $element) {
 	     		window.location = 'hello';
 	        });  	
 	    }
 	</script>
 	
 	 <script>
 	$('#my-rewards-table').bootstrapTable({
 	    "columns": [{
 	        "field": "proj",
 	        "title": "Project Name"
 	    }, {
 	        "field": "needed",
 	        "title": "Money needed"
 	    }, {
 	        "field": "description",
 	        "title": "Description"
 	    }],
 	    "data": [{
 	        "proj": "Futurama",
 	       	"needed": "1000",
 	       	"description": "Fry",
 	    }, {
 	    	"proj": "Futurama",
 	    	"needed": "1000",
  	       	"description": "Fry"
 	    }]
 	});
 	</script>
</body>
</html>