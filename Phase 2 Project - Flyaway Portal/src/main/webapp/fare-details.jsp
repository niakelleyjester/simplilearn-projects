<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fare Details</title>
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
				<h2>Fare Details</h2>
			</div><!-- end row -->			
		</div><!-- end container2 -->
		<!-- table -->
		<table border="1" class="table table-striped">
			<tr>
				<th>Flight Number</th>
				<th>Airline</th>
				<th>Travel Class</th>
				<th>Fare</th>					
				<th>Action</th>			
			</tr>
			
			<!-- As a best practice, use JSTL Tags (instead of JSP Scriptlet Code). Makes the code more readable. -->		
			<c:forEach var="tempFare" items="${FARE_DETAILS_LIST}">
				<!--  set up a link for each user using a JSTL feature called URL -->
				<!-- These 2 parameters are going to the Book Flight Controller Servlet -->
				<!-- We want to actually prepopulate the form on load on the booking details page. 
				     with that flight's information from the database.  -->	
				<c:url var="tempLink" value="BookFlightControllerServlet">
					<c:param name="command" value="BOOK_FARE" />
					<c:param name="flightNumber" value="${tempFare.searchResult.flightNumber}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="airline" value="${tempFare.searchResult.airlineName}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="day" value="${tempFare.searchResult.day}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="date" value="${SELECTED_DATE}"/> 
					<c:param name="src" value="${tempFare.searchResult.sourceAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="srcFullName" value="${tempFare.searchResult.sourceAiportFullName}" />
					<c:param name="dest" value="${tempFare.searchResult.destinationAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="destFullName" value="${tempFare.searchResult.destinationAiportFullName}" />					
					<c:param name="travelClass" value="${tempFare.travelClass}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="fareUnitCost" value="${tempFare.fare}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->							
				</c:url>
				<tr>
					<td>${tempFare.searchResult.flightNumber}</td>
					<td>${tempFare.searchResult.airlineName}</td>
					<td>${tempFare.travelClass}</td>
					<td>$${tempFare.fare}</td>
					<c:if test="${sessionScope.loggedInUser!=null}">
						<td><a href="${tempLink}" class="button">Book Now</a></td> <!-- So, this'll basically create an HREF with the text of Update, and then embedded is the actual tempLink that has the command and the flight number. -->
					</c:if>
					<c:if test="${sessionScope.loggedInUser == null }">
						<td>Please Login to Book Ticket</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<p>
			<a href="javascript:history.back()">Back to Search Results</a>
		</p>
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