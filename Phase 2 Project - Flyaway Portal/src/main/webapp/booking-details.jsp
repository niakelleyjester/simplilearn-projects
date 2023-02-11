<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fare Booking Details</title>
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
					<h2>Booking Details</h2>
				</div><!-- end row -->			
			</div><!-- end container2 -->
			
			<!--  table -->
	        <table border="1" class="table">
	        <tr>
	        	<th>Flight Number</th>
				<td>${selectedFlightNumber}</td>
	        </tr>
	        <tr>
	        	<th>Airline</th>
	        	<td>${selectedAirline}</td>
	        </tr>
	        <tr>
	        	<th>Travel Class</th>
	        	<td>${selectedTravelClass}</td>
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
	        </tr>
	        <tr>
	        	<th>Travel Date</th>
	        	<td>${selectedDate}</td>
	        </tr>
	        <tr>
	        	<th>No. of Passengers</th>
	        	<td>${sessionScope.passengers}</td>
	        </tr>
	        <tr>
	        	<th>Total Amount to be Paid</th>
	        	<td>$${selectedTotalCost}</td>
	        </tr>
	        
	        <tr>
	        	<th>Passenger Details</th>
        	</tr>
        	<tr>	        	
	        	<th>Customer Id:</th>
	        	<td>${sessionScope.loggedInUser.userid}</td>
        	</tr>
	        <tr>
	        	<th>Customer Name:</th>
	        	<td>${sessionScope.loggedInUser.firstName} ${sessionScope.loggedInUser.lastName}</td>
        	</tr>
        	<tr>
	        	<th>Email Address:</th>
	        	<td>${sessionScope.loggedInUser.email}</td>
	        </tr>
	        <tr>
	        	<th>Phone Number:</th>
	        	<td>${sessionScope.loggedInUser.phoneNumber}</td>
	        </tr>	        
	        </table>
	        </main>
	        <c:url var="tempLink" value="PaymentServlet">
					<c:param name="command" value="PAY" />
			</c:url>
			<a href="${tempLink}" class="button">Go to Payment</a>
	        <!--   <a href="payment-form.jsp" class="button">Go to Payment</a>-->  <!--  should go to payment form -->
	        <!--<button type="submit">Go to Payment</button>-->
	        <a href="javascript:history.back()">Back to Fare Details</a>
	
	
	
	<footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022-2023 FlyAway, Inc.</p>    
    </footer>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</html>