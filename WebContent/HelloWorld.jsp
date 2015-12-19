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
				<h1>Futurama</h1>
			</div>
		</div>
		
		<div class="row project-title">
			<div class="col-md-8">
				<p>Futurama is an American adult animated science fiction sitcom created by Matt Groening for the Fox Broadcasting Company. The series follows the adventures of a late-20th-century New York City pizza delivery boy, Philip J. Fry, who, after being unwittingly cryogenically frozen for one thousand years, finds employment at Planet Express, an interplanetary delivery company in the retro-futuristic 31st century. The series was envisioned by Groening in the late 1990s while working on The Simpsons, later bringing Cohen aboard to develop storylines and characters to pitch the show to Fox.</p>
			</div>
			<div class="col-md-4">
				<table id="tablestatus" data-card-view="true"></table>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-8">
				<p>In the United States, the series aired on Fox from March 28, 1999, to August 10, 2003, before ceasing production. Futurama also aired in reruns on Cartoon Network's Adult Swim from 2002 to 2007, until the network's contract expired. It was revived in 2008 as four direct-to-video films; the last of which was released in early 2009. Comedy Central entered into an agreement with 20th Century Fox Television to syndicate the existing episodes and air the films as 16 new, half-hour episodes, constituting a fifth season.</p>
			</div>
			<div class="col-md-4">
				<p><b>Rewards</b></p>
				<table id="tablerewards" class="project-rewards" data-card-view="true"></table>
			</div>
		</div>
		
		<div class="row project-info">
			<div class="col-md-8">
			</div>
			<div class="col-md-4">
				<a href="<s:url action='forum'/>">Check the Forum</a>
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
    	websocket.send("15");  // TODO: IDPROJECT!!!!!!!!!!!!!!!!!!!!!!!!!
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
 	<!-- Populate Rewards Grid -->
 	 <script>
 	$('#tablerewards').bootstrapTable({
 	    columns: [{
 	        field: 'money',
 	        title: 'Tier'
 	    }, {
 	        field: 'reward',
 	        title: 'Reward'
 	    }],
 	    data: [{
 	        money: '15',
 	       	reward: 'You get a backer T-Shirt',
 	    }, {
 	        money: '35',
 	       	reward: 'You get a Toy of Bender',
 	    }]
 	});
 	</script>
</body>
</html>