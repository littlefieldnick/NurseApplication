<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.lang.reflect.*" %>
<%@ page import="edu.usm.cos420.model.*" %> 
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nurses</title>
</head>
<body>

<h2> Delete Nurse Info</h2>
            
    <c:if test="${deletedSuccess}">
	     The nurse has been successfully removed from the database. 
	</c:if>
	<c:if test="${not deletedSuccess}">
		The nurse could not be removed from the database. 
	</c:if>
	
	<form action="DeleteNurse" method="GET">
		<button type="submit">Delete another Nurse from the Database.</button>
	</form>
	<br>
	
	<form action="AddNurse.html" method="GET">
		<button type="submit">Add a Nurse to the Database.</button>
	</form>
	<br>
	<form action="DisplayNursesDB" method="GET">
		<button type="submit">View the Nurse list.</button>
	</form>
	<br>
	<form action="index.jsp" method="GET">
		<button type="submit">Back to index page.</button>
	</form>
</body>
</html>