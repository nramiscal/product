<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

span{
	font-weight: normal;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>
    <h1>Welcome <c:out value="${currentUser.firstName}"></c:out>!</h1>
 
   <h3>Current Package: <span>${currentUser.getPackages().get(0).name }</span></h3>
    <h3> Next Due Date: <span>${currentUser.getNextDueDate()}</span></h3>
    <h3>Amount Due: $<span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${currentUser.getPackages().get(0).price }"></fmt:formatNumber></span></h3>
    <%-- <h3>User Since: <span><fmt:formatDate pattern = "EEEEE, MMM d, yyyy" value = "${currentUser.created_at }" /> at <fmt:formatDate type = "time" timeStyle = "short" value = "${currentUser.created_at}" /></span></h3> --%>
    <h3>User Since: <span><fmt:formatDate pattern = "MMMMM d, yyyy" value = "${currentUser.created_at }" /></span></h3>

    <br>
    <br>

    
    
   <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout" />
    </form>
    
</body>
</html>