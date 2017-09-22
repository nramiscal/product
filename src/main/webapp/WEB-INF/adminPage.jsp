<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
	
	* {
		font-family: sans-serif;
	}
	
	#container {
		width: 1000px;
		margin: 0px auto;
	}
	
	table {
	    font-family: arial, sans-serif;
	    border-collapse: collapse;
	    width: 100%;
	}
	
	td, th {
	    border: 1px solid #dddddd;
	    text-align: left;
	    padding: 8px;
	}
	 
	tr:nth-child(even) {
	    background-color: #dddddd;
	}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin Dashboard</title>
</head>
	<body>
		<div id="container">
			<h1>Welcome ${currentUser.firstName}</h1>
			
			<h2>Customers</h2>
			<table>
			  <tr>
			    <th>Name</th>
			    <th>Email</th>
			    <th>Action</th>
			  </tr>
			<c:forEach items="${users}" var="user" varStatus="loop">
			<tr>    
			    <td><c:out value="${user.firstName}"/></td>
			    <td><c:out value="${user.username}"/></td>				
				<c:choose>
				  <c:when test="${user.checkIfAdmin() == 'true'}">
				   	<td>Admin | <a href="/makeUser/${user.id}">Make User</a></td>
				  </c:when>
				  <c:otherwise>
				    <td><a href="/delete/${user.id}">Delete</a> | <a href="/makeAdmin/${user.id}">Make Admin</a></td>
				  </c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
			</table> 
			
			
			<h2>Packages</h2>
						<table>
			  <tr>
			    <th>Package Name</th>
			    <th>Package Cost</th>
			    <th>Available</th>
			    <th>Number of Users</th>
			    <th>Actions</th>
			  </tr>
			<c:forEach items="${packages}" var="pkg">
  			<tr>    
			    <td>${pkg.name}</td>
			    <td><c:out value="${pkg.price}"/></td>
			    
			    <!-- AVAILABLE FIELD IN MODEL IS NOT WORKING!!! -->
			    <!-- tried saving as a boolean and column was empty -->
			    <!-- tried saving as 0 or 1 and still cannot access, even though can see value in mysql :( :( :( -->
			    
			    <td> unavailable </td>
			    <td>${pkg.users.size() }</td>
			    <td><a href="activate/${pkg.id}">Activate</a></td>

<%--  			    	<c:choose>
 				  <c:when test="${pkg.available} = 1">
				   	<td>available</td>
				  </c:when>
				  <c:when test="${pkg.available} = 0">
				   	<td>unavailable</td>
				  </c:when>
				</c:choose>
					
				<c:choose>
 				  <c:when test="${pkg.available} == 1">
				   	<td>Admin | <a href="/deactivate/${pkg.id}">Deactivate</a></td>
				  </c:when>
				  <c:when test="${pkg.available} == 0">
				   	<td>Admin | <a href="/deactivate/${pkg.id}">Deactivate</a></td>
				  </c:when>
				  <c:when test="${pkg.available} == 1">
				   	<td>Admin | <a href="activate/${pkg.id}">Activate</a></td>
				  </c:when>
				</c:choose> --%>
			</tr>
			</c:forEach>
			</table> 
			
			
<%--			<h2>Create Package</h2>
 			    <c:if test="${errorMessage != null}">
       			 <c:out value="${errorMessage}"></c:out>
    				</c:if>
			
			<form:form method="POST" action="/packages/new" modelAttribute="pkg">
				<form:hidden path="id"/>
			    <form:label path="name">Name
			    <form:errors path="name"/>
			    <form:input path="name"/></form:label><br>
			    
			    	<form:label path="price">Price
			    <form:errors path="price"/>
			    <form:input path="price"/></form:label><br>
			    
			    <input type="submit" value="Create"/>
			</form:form> --%>
			
			<br>
			<br>
			<a href="/packages/new">Create Package</a>
			
			<br>
			<br>
			<br>
			    <form id="logoutForm" method="POST" action="/logout">
		    		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    	    		<input type="submit" value="Logout" />
			    </form>
			    
			    
			    <br>
			    <br>


<%--     <c:forEach items="${packages}" var="entry">
        ${entry.isAvailable}<br>
        ${entry.name}<br>
    </c:forEach> --%>

			    
			
				
		</div>
	</body>
</html>