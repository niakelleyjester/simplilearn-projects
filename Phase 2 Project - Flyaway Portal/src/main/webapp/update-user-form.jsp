<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update User Form</title>
	<link rel="shortcut icon" href="images/favicon.ico">	
	<!-- Linked CSS-->  
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">  
    <link type="text/css" rel="stylesheet" href="css/flyawaystyles.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
</head>
<body>
	<div id="wrapper">
		<div id="header">
		<h2>Update User</h2>
		</div><!-- end header -->
	</div><!-- end wrapper -->
	
	<div id="container">
		<div id="content">
		
		<form action="UserControllerServlet" method="GET">
		
				<!-- Hidden Fields -->
				<input type="hidden" name="command" value="UPDATE" />
				<input type="hidden" name="userId" value="${THE_USER.userid}" />
				
				<table>
				<tbody>
						<tr>
							<td><label>First Name: </label></td>
							<td><input type="text" name="firstName" value="${THE_USER.firstName}"/></td>
						</tr>
						<tr>
							<td><label>Last Name: </label></td>
							<td><input type="text" name="lastName" value="${THE_USER.lastName}"></td>
						</tr>
						<tr>
							<td><label>Username: </label></td>
							<td><input type="text" name="username" value="${THE_USER.userName}"></td>
						</tr>
						<tr>
							<td><label>Email: </label></td>
							<td><input type="text" name="email" value="${THE_USER.email}"></td>
						</tr>
						<tr>
							<td><label>Password: </label></td>
							<td><input type="password" name="pwd" value="${THE_USER.password}"></td>						
							<!-- <a href="${tempLink}">Change Password</a> -->
						</tr>
						<tr>
							<td><label>Phone Number: </label></td>
							<td><input type="text" name="phoneNumber" value="${THE_USER.phoneNumber}"></td>
						</tr>						
						<tr>
							<td><label></label></td>						
							<td><input class="save" type="submit" value="Save"></td>
						</tr>							
					</tbody>
				</table>
		</form>
		<c:if test="${sessionScope.isAdminUser}">
			<div style="clear: both;"></div><!-- end clear -->
				<p>
					<a href="UserControllerServlet">Back to User List</a>
				</p>
		</c:if>
		</div><!-- end content -->
	</div><!-- end container -->
	
	<footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022-2023 FlyAway, Inc.</p>    
    </footer>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</html>