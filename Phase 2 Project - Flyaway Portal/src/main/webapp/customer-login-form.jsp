<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Customer Login Form</title>
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
                <h2>Customer Login</h2>
             </div><!-- end row -->
            
            <!--  Row 2 -->
            <div class="row">
               	<form action="LoginControllerServlet" method="post">
                	<label for="userName"><strong>User Name</strong></label>
    				<input type="text" placeholder="Enter Username" name="userName" required><br>
    				               		
                   	<label for="password"><strong>Password</strong></label>
    				<input type="password" placeholder="Enter Password" name="password" required><br>
    				
    				<label for="email"><strong>Email</strong></label>
    				<input type="text" placeholder="Enter Email Address" name="email"><br>
    				                   	              	
                    <button type="submit">Login</button>
                   </form>                
            </div><!-- end row -->  
        </div><!--  end container2 -->              
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