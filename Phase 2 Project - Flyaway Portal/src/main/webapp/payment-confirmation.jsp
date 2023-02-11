<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Payment Confirmation</title>
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
            <div class="row alert alert-success" role="alert">                
                <h2>Payment Successful</h2> <br><br>
            </div><!-- end row -->
        	<!--  Row 1 -->
            <div class="row">                
                <h2>Payment Confirmation</h2> <br><br>            
            </div><!-- end row -->            
            
            <!--  table -->
	        <table border="1" class="table">
	        <tr>
	        	<th>Booking Id</th>
				<td>${purchasedTicket.bookingid}</td>
	        </tr>
	        <tr>
	        	<th>Customer</th>
				<td>${sessionScope.loggedInUser.firstName} ${sessionScope.loggedInUser.lastName}</td>
	        </tr>
	        <tr>
	        	<th>Flight Number</th>
				<td>${purchasedTicket.flightNumber}</td>
	        </tr>
	        <tr>
	        	<th>Source</th>
	        	<td>${selectedSrc} | ${selectedSourceFN}</td>
	        </tr>
	        <tr>
	        	<th>Destination</th>
	        	<td>${selectedDest} | ${selectedDestFN}</td>
	        </tr>
	        <tr>
	        	<th>Travel Day</th>
	        	<td>${selectedDay}</td>
	        <tr>
	        	<th>Travel Date</th>
	        	<td>${selectedDate}</td>
	        </tr>
	        <tr>
	        	<th>No. of Passengers</th>
	        	<td>${purchasedTicket.totalPassengers}</td>
	        </tr>
	        <tr>
	        	<th>Total Amount to be Paid</th>
	        	<td>$${purchasedTicket.totalFare}</td>
	        </tr>
	        	        
	        </table> 
            
            <a href="javascript:history.back()">Back to Payment Form</a>
            
     	</div><!--  end container2 -->              
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