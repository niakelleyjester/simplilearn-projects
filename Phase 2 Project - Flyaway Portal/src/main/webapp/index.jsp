<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Airline Booking Portal for FlyAway.com</title>
    <link rel="shortcut icon" href="images/favicon.ico">
    <!-- Linked CSS-->
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">  
    <link type="text/css" rel="stylesheet" href="css/flyawaystyles.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
</head>
<body>
    <main>
        <div class="container" style="text-align:center">
            <h1>Welcome to FLYAWAY</h1>
            <h2>Your Journey is one click away</h2>
		    <br><br>
		    <a class="button" href="BookFlightControllerServlet">Click Here to Enter Site</a>
        </div>
    </main>         

    <footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022 FlyAway, Inc.</p>    
    </footer>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</html>