<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Fundstarter - Project</title>
	
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
				<h1> ${projTitle} </h1>
			</div>
		</div>
		
		<div class="row project-title">
			<div class="col-md-8">
				<p>${projDescri}</p>
			</div>
			<div class="col-md-4">
				<table id="tablestatus" data-card-view="true"></table>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-8">
			</div>
			<div class="col-md-4">
				<a href="<s:url action='forum'/>" class="btn btn-primary btn-large">Forum</a>
				<input type="button" class="btn btn-primary btn-large" value="Show Rewards" onclick="showRewardsProject()" />
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-8">
			</div>
			<div class="col-md-4">
				<center><h4>Admin Tools</h4></center>
				<input type="button" class="btn btn-danger btn-large" value="Delete Project" onclick="deleteProject()" />
				<a href="<s:url action='createProjectPage'/>" class="btn btn-primary btn-large">Add Reward</a>
				<a href="<s:url action='createProjectPage'/>" class="btn btn-primary btn-large">Delete Reward</a>
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
 	<!-- Populate Status Grid -->
 	<script>
 	var websocket = null;
 	var $table = $('#tablestatus');
 	var projectId = "<s:property value='projId'/>";
 	console.log(projectId);
 	
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
    	websocket.send("projid-"+projectId); 
    	
    }
    
    function deleteProject(event) {
    	window.location='deleteProject'+projectId;
    }
    
    function showRewardsProject(event) {
    	window.location='showRewards'+projectId;
    }
    
    function onMessage(message) { // print the received message
        writeToHistory(message.data);
        console.log(message.data);
    }
    
    function writeToHistory(text) {
    	var jsonstuff = JSON.parse(text);
    	$table.bootstrapTable('destroy');
    	
    	$table.bootstrapTable({
     	    columns: [{
     	        field: 'active',
     	        title: 'Status'
     	    }, {
     	        field: 'progress',
     	        title: 'Goal'
     	    }, {
     	        field: 'endDate',
     	        title: 'Final Date'
     	    }],
     	    data: [jsonstuff]
     	});
    	
     	$table.bootstrapTable('hideColumn', 'projId');
     	
     	$table.on('click-row.bs.table', function (e, row, $element) {
     		window.location = 'hello';
        });  	
    }
 	</script>
</body>
</html>