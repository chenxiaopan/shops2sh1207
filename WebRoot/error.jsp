<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Error</title>
	</head>

	<body>
		<h4>This application has malfunctioned.</h4>
		<p>  Please read through the following detailed account of the
		problems encounted during the processing of your request.  After determining what mistakes you
		may have made, please resubmit your request.  Better luck next time! </p> 
		
		<p><h4>Exception Name: </h4><s:property value="exception" /></p> 
		<p><h4>What you did wrong:</h4> <s:property value="exceptionStack" /></p> 
		
		<h5>Also, please confirm that your Internet is working before actually contacting us.</h5>
	  
		
	</body>
	
</html>
