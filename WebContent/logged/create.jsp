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
	
	<title>Fundstarter - Create a Project</title>
	
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
				<s:form theme="simple" action="projectCreation" class="form-horizontal" role="form">
					<div class="container">
				    	<h4>Project Name:</h4>
				      	<s:textfield name="createProjectStoreBean.projName" cssClass="form-control" placeholder="Pick a catchy name"/>
				    	<h4>Short Description:</h4>
				      	<s:textarea cssClass="form-control" name="createProjectStoreBean.description" placeholder="Explain your idea"/>
					  	<h4>End Date:</h4>
					  	<h5>Day:</h5>
					    <s:textfield cssClass="form-control" name="createProjectStoreBean.dateDay" placeholder="ex: 02"/>
					    <h5>Month:</h5>
					    <s:textfield cssClass="form-control" name="createProjectStoreBean.dateMonth" placeholder="ex: 02"/>
					    <h5>Year:</h5>
					    <s:textfield cssClass="form-control" name="createProjectStoreBean.dateYear" placeholder="ex: 2112"/>
					  	<h4>Minimum amount:</h4>
					    <s:textfield cssClass="form-control" name="createProjectStoreBean.amount" placeholder="Tell us how much you need"/>
				  	</div>
				  	
			    	<div class="col-sm-3"> 
				    	<h4>Rewards (Obligatory):</h4>
				    	<h5>Short description:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardDescription1" placeholder="Explain your reward"/>
				    	<h5>Minimum amount for pledge:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardAmount1" placeholder="Tell us the minimum needed"/>
			   		</div>
			   		
			   		<div class="col-sm-3"> 
				    	<h4>Rewards (Obligatory):</h4>
				    	<h5>Short description:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardDescription2" placeholder="Explain your reward"/>
				    	<h5>Minimum amount for pledge:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardAmount2" placeholder="Tell us the minimum needed"/>
			   		</div>
			   		
			   		<div class="col-sm-3"> 
				    	<h4>Rewards (Obligatory):</h4>
				    	<h5>Short description:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardDescription3" placeholder="Explain your reward"/>
				    	<h5>Minimum amount for pledge:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardAmount3" placeholder="Tell us the minimum needed"/>
			   		</div>
			   		
			   		<div class="col-sm-3"> 
				    	<h4>Rewards (Obligatory):</h4>
				    	<h5>Short description:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardDescription4" placeholder="Explain your reward"/>
				    	<h5>Minimum amount for pledge:</h5>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.rewardAmount4" placeholder="Tell us the minimum needed"/>
			   		</div>
			   		
			   		<div class="col-sm-3"> 
				    	<h4>Vote Options (Optional):</h4>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.voteOption1" placeholder="Vote option Description"/>
				    	<s:textfield cssClass="form-control" name="createProjectStoreBean.voteOption2" placeholder="Vote option Description"/>
			   		</div>
			   		<s:submit cssClass="btn btn-primary btn-block" label="Submit" />
				</s:form>
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