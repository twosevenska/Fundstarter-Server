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
				<h1>Rewards</h1>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-12">
			<table id="tablerewards" class="projects-table"></table></div>
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
 	var $table2 = $('#tablerewards');
 	var projectId = "<s:property value='projId'/>"; 	
 	
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
    	websocket.send("rewardsid-"+projectId); 
    }
    
    function onMessage(message) { // print the received message
        writeToHistory(message.data);
        console.log(message.data);
    }
    
    function writeToHistory(text) {
    	var jsonstuff = JSON.parse(text);
    	
    	$table2.bootstrapTable('destroy');
     	$table2.bootstrapTable(jsonstuff);
     	$table2.bootstrapTable('hideColumn', 'projId');
     	
     	$table2.on('click-row.bs.table', function (e, row, $element) {
     		window.location = 'hello';
        });  	
    }
 	</script>
</body>
</html>