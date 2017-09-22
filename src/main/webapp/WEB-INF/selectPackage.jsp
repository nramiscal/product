<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

#container {
	width: 1000px;
	margin: 0px auto;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Package</title>
</head>
<body>


   	<div id="container">
   	
   	<h1>Select Package</h1>
	<h3>Please choose a subscription and start date</h3>



   <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout" />
    </form>


	</div>
</body>
</html>