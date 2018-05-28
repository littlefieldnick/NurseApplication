<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.lang.reflect.*"%>
<%@ page import="edu.usm.cos420.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Nurse> nurses=
            (ArrayList<Nurse>)request.getAttribute("nursesList");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nurses</title>
</head>
<body>

	<h2>Delete a Nurse</h2>
	<% for(int i = 0; i < nurses.size(); i++){
				Nurse n = nurses.get(i); %>
	<%=n%>
	<form action="DeleteNurse" method="post">
		<input type="hidden" name="nurseId" value="<%= n.getId()%>">
		<button type="submit">Delete Nurse</button>
	</form>
	<% } %>

	<form action="AddNurse.html" method="GET">
		<button type="submit">Add a Nurse to the Database</button>
	</form>
	<br>
	<form action="DisplayNursesDB" method="GET">
		<button type="submit">View the Nurse list</button>
	</form>
	<br>
	<form action="index.jsp" method="GET">
		<button type="submit">Back to index page.</button>
	</form>
</body>
</html>