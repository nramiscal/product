<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
   	
   	<h1>Welcome to Dojoscriptions ${currentUser.firstName } !</h1>
	<h3>Please choose a subscription and start date</h3>
	
	
	<form action="/subscribe" method="post">
	Due Day:
	<input type="number" name="startday" min="1" max="31" value="1">
	<br><br>
	Package:
	<select name="pkg_id">
		<c:forEach items="${subpackages}" var="subpkg" varStatus="loop">
			<option value="${subpkg.id}">${subpkg.name}  ${subpkg.price}</option>
		</c:forEach>
	</select>
	<br><br>
	<input type="hidden" name="currentUser_id" value="${currentUser.id}"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="submit">
	</form>
	
		
		
   <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout" />
    </form>


	</div>
</body>
</html>