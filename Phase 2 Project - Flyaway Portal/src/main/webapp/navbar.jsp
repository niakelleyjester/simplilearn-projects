<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" href="images/favicon.ico">
    <!-- Linked CSS-->
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">  
    <link type="text/css" rel="stylesheet" href="css/flyawaystyles.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
</head>
<body>
<header>
	<div id="topnav">
		<div>		 
			<a href="index.jsp">
				<img class="img-test split" src="images/Flyaway Logo.jpg" alt="Flyaway Logo">
			</a>		 
		</div>		
        <!-- Navigation Bar -->    
        <nav id="nav_menu">
        	<ul>  
				<li><a href="index.jsp">Home</a></li>
	            <li><a href="BookFlightControllerServlet">Book Flight</a></li>
	            <c:if test="${sessionScope.loggedInUser==null}">
	            	<li><a href="admin-login-form.jsp">Admin Login</a></li>
	                <li><a href="customer-login-form.jsp">Customer Login</a></li>
	                <li><a href="register-new-user-form.jsp">Register New User</a>
	            </c:if>                
	            <c:if test="${sessionScope.isAdminUser}">
	            				<li><a href="UserControllerServlet">List Users</a></li>
	            				<li><a href="register-new-user-form.jsp">Register New User</a>				
	          					<li><a>Airline Management</a></li>
	                   			<li><a>Fare Management</a></li>
	            </c:if>	
	            <c:if test="${sessionScope.loggedInUser!=null}">
	            	<c:url var="tempLink" value="UserControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${sessionScope.loggedInUser.userid}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					</c:url>
	            	<li><a href="${tempLink}">Update Profile</a></li>
	            	<c:url var="tempLink2" value="UserControllerServlet">
						<c:param name="command" value="HISTORY" />
						<c:param name="userId" value="${sessionScope.loggedInUser.userid}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					</c:url>
	            	<li><a href="${tempLink2}">Booking History</a>
	            	<li><a href="LogoutControllerServlet">Logout</a></li>
	            </c:if>
            </ul>   
        </nav>  <!-- end nav bar -->       
    </div><!-- end topnav -->
    </header>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</html>