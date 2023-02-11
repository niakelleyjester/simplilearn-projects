<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link rel="shortcut icon" href="images/favicon.ico">	
	<!-- Linked CSS-->  
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">  
    <link type="text/css" rel="stylesheet" href="css/flyawaystyles.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >	
</head>

<body>
		<main>
    	<div class="container2">
        	
        	<!--  Row 1 -->
            <div class="row">
				<h2>User List</h2>
			</div><!-- end row -->
			
			<!--  Row 2 -->
            <div class="row"> 				
				<!-- new button for Add User -->
				<!-- Using JavaScript to redirect the browser to a new page -->
				<button type="submit"
					   class="add-user-button"	 
					   onClick="window.location.href='register-new-user-form.jsp'; return false;">
					   Add User
				</button>	
				<br><br>
			</div><!-- end row -->
		</div><!-- end container2 -->				
						
		<!-- table -->
		<table border="1" class="table table-striped">
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">User Name</th>
					<th scope="col">Role</th>
					<th scope="col">Email</th>	
					<th scope="col">Phone Number</th>	
					<th scope="col">Action</th>			
				</tr>
			</thead>
			
			<tbody>			
				<!-- As a best practice, use JSTL Tags (instead of JSP Scriptlet Code). Makes the code more readable. -->			
				<c:forEach var="tempUser" items="${USERS_LIST}">
				
					<!--  set up a link for each user using a JSTL feature called URL -->
					<!-- These 2 parameters are going to the User Controller Servlet -->
					<!-- We want to actually prepopulate the form on load. 
					     So when the user selects a user to update, we will prepopulate the form with that user's information from the database.  -->	
					<c:url var="tempLink" value="UserControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${tempUser.userid}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					</c:url>	
					
					<c:url var="deleteLink" value="UserControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${tempUser.userid}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					</c:url>
				
					<tr>
						<td>${tempUser.firstName}</td><!-- Will actually call tempUser.getFirstName() due to JSP expression language -->
						<td>${tempUser.lastName}</td><!-- Will actually call tempUser.getLastName() due to JSP expression language -->
						<td>${tempUser.userName}</td><!-- Will actually call tempUser.getUserName() due to JSP expression language -->
						<td>${tempUser.role}</td><!-- Will actually call tempUser.getRole() due to JSP expression language -->
						<td>${tempUser.email}</td><!-- Will actually call tempUser.getEmail() due to JSP expression language -->
						<td>${tempUser.phoneNumber}</td>
						<td>
							<a href="${tempLink}">Update</a> <!-- So, this'll basically create an HREF with the text of Update, and then embedded is the actual tempLink that has the command and the User ID. -->
							|
							<!-- onclick handler is Javascript. proof that you can integrate Javascript with JSP technology -->
							<a href="${deleteLink}" 
							onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>						
						</td>																		
					</tr>	
				</c:forEach>
			</tbody>				
		</table>
	</main>
	<footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022-2023 FlyAway, Inc.</p>    
    </footer>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</html>