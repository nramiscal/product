<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


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
			
			<h2>All Customers</h2>
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
			
			<h2>Current Subscribers</h2>
			<table>
			  <tr>
			    <th>Name</th>
			    <th>Next Due Date</th>
			    <th>Amount Due</th>
			    <th>Package Type</th>
			  </tr>
			<c:forEach items="${subscribers}" var="_user" varStatus="loop">
			<tr>
				<td><c:out value="${_user.firstName}"/></td>   
				<td><c:out value="${_user.getNextDueDate()}"/></td> 
				<td>$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${_user.getPackages().get(0).price}"></fmt:formatNumber></td>
 			    <td><c:out value="${_user.getPackages().get(0).name}"/></td>
		
			</tr>
			</c:forEach>
			</table> 
			
			
			<h2>Packages</h2>
			<table>
			  <tr>
			    <th>Package Name</th>
			    <th>Package Cost</th>
			    <th>Availability</th>
			    <th>Number of Users</th>
			    <th>Actions</th>
			  </tr>
			<c:forEach items="${packages}" var="pkg">
  			<tr>    
			    <td>${pkg.name}</td>
			    <td><c:out value="${pkg.price}"/></td>		    
			    <td><c:out value="${pkg.availability}"/></td>
			    <td>${pkg.users.size() }</td>
					
  				<c:choose>
  				  <c:when test="${pkg.checkIfAvailable() && pkg.users.size() == 0}">
				   	<td><a href="/deactivate/${pkg.id}">Deactivate</a> | <a href="/delete_package/${pkg.id}">Delete</a></td>
				  </c:when>
 				  <c:when test="${pkg.checkIfAvailable()}">
				   	<td><a href="/deactivate/${pkg.id}">Deactivate</a></td>
				  </c:when>
  				  <c:when test="${!pkg.checkIfAvailable()}">
				   	<td><a href="/activate/${pkg.id}">Activate</a></td>
				  </c:when>
 
				</c:choose>
			</tr>
			</c:forEach>
			</table> 
			
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

				
		</div>
	</body>
</html>