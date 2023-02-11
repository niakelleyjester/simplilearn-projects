<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New User Registration</title>
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
                    <h2>User Registration Form</h2>
            </div><!-- end row -->
            
            <!--  Row 2 -->
            <div class="row"> 
                    <!-- Originally, I had /saveUser, which produced an error. 
                    Why? The url in the action in your form starts with a slash. 
                    That implies that you are starting from the server root and looking for a servlet named register at that point. 
                    However, your servlet is actually in a context of some sort. So you can either say: /mycontext/register or remove the slash altogether. -->
                    <!--  <form name="userRegistrationForm" action="saveUser" method="post"> -->
                    <form name="userRegistrationForm" action="UserControllerServlet" method="get">
                    	<input type="hidden" name="command" value="ADD">
                        <!--  id is the object of class-->
                        <!--<jsp:useBean id="obj" class = "com.flyaway.model.User" scope="session"></jsp:useBean> -->
                        <label for="fname"><strong>First Name</strong></label>
                        <input type="text" placeholder="Enter First Name" name="fname"><br>
                        
                        <label for="lname"><strong>Last Name</strong></label>
                        <input type="text" placeholder="Enter Last Name" name="lname"><br>
                        
                        <label for="uname"><strong>User Name</strong></label>
    					<input type="text" placeholder="Enter Username" name="uname" required><br>
    					
    					<label for="email"><strong>Email</strong></label>
    					<input type="text" placeholder="Enter Email Address" name="email"><br>
    				               		
                   		<label for="password"><strong>Password</strong></label>
    					<input type="password" placeholder="Enter Password" name="password" required><br>
                        
                        <label for="phonenumber"><strong>Phone Number</strong></label>
                        <input type="text" placeholder="Enter Phone Number" name="phonenumber"><br><br>
                         
                        <label for="userrole"><strong>Role</strong></label>                        
                        <input type="radio" name="userrole" value="Customer" checked>Customer
                        <input type="radio" name="userrole" value="Admin">Administrator<br>
                        
                        <button type="submit">Submit</button>
                    </form>
            </div><!-- end row --> 
        </div> <!--  end container2 -->       
	</main>
	<footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022-2023 FlyAway, Inc.</p>    
    </footer>   
</body>
</html>