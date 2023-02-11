<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Search Results</title>
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
				<h2>Search Results</h2>
			</div><!-- end row -->
			<!-- Row 2 -->
			<div class="row">
				<h3>Departing Flights: </h3>				
			</div>
			<!-- Row 3 -->
			<div class="row">
				<small class="text-muted">
					${sessionScope.departAC} to ${sessionScope.arrivalAC}
				</small>
			</div>
			<!-- Row 4 -->
			<div class="row">
				<small class="text-muted">
					${sessionScope.departingAirportFullName} to ${sessionScope.arrivalAirportFullName}
				</small>
			</div>
        </div> <!--  end container2 -->         
        
        <!--  table -->
        <table border="1" class="table table-striped">        
			<tr>
				<th>Flight Number</th>
				<th>Airline</th>
				<th>Departure Travel Day</th>
				<th>Departure Travel Date</th>				
				<th>Source Airport</th>
				<th>Destination Airport</th>					
				<th>Action</th>			
			</tr>
			
			<!-- As a best practice, use JSTL Tags (instead of JSP Scriptlet Code). Makes the code more readable. -->		
			<c:forEach var="tempDepartResult" items="${DEPARTING_SEARCH_RESULTS_LIST}">
				<!--  set up a link for each user using a JSTL feature called URL -->
				<!-- These 2 parameters are going to the Book Flight Controller Servlet -->
				<!-- We want to actually prepopulate the form on load on the booking details page. 
				     with that flight's information from the database.  -->	
				<c:url var="tempLink" value="BookFlightControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="flightNumber" value="${tempDepartResult.flightNumber}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="airline" value="${tempDepartResult.airlineName}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="day" value="${tempDepartResult.day}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="date" value="${departDate}" />
					<c:param name="src" value="${tempDepartResult.sourceAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="srcFullName" value="${tempDepartResult.sourceAiportFullName}" />
					<c:param name="dest" value="${tempDepartResult.destinationAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
					<c:param name="destFullName" value="${tempDepartResult.destinationAiportFullName}" />	
				</c:url>
				<tr>
					<td>${tempDepartResult.flightNumber}</td><!-- Will actually call tempUser.getFlightNumber() due to JSP expression language -->
					<td>${tempDepartResult.airlineName}</td>
					<td>${tempDepartResult.day}</td>
					<td>${departDate}</td>
					<td>${tempDepartResult.sourceAirport} | ${tempDepartResult.sourceAiportFullName}</td>
					<td>${tempDepartResult.destinationAirport} | ${tempDepartResult.destinationAiportFullName}</td>												
					<td><a href="${tempLink}" class="button">Book Tickets</a></td> <!-- So, this'll basically create an HREF with the text of Book Tickets, and then embedded is the actual tempLink that has the command and the flight number. -->
				</tr>				
			</c:forEach>			
		</table>
		
		<c:if test="${fn:length(DEPARTING_SEARCH_RESULTS_LIST) == 0}">
				<div class="row alert alert-light" role="alert">                
                <h2>Your search did not yield any results</h2> <br><br>
            </div><!-- end row -->
		</c:if>
        
        <c:if test="${sessionScope.flightDirection == 'roundtrip'}" >        
	        <div class="container2">
	        	<!--  Row 1 -->
	            <div class="row">
	        		<h3>Returning Flights: </h3>
	    		</div>
	    		<!-- Row 2 -->
				<div class="row">
					<small class="text-muted">						
						${sessionScope.arrivalAC} to ${sessionScope.departAC}						
					</small>
				</div>
				<!-- Row 3 -->
				<div class="row">					
					<small class="text-muted">${sessionScope.arrivalAirportFullName} to ${sessionScope.departingAirportFullName}</small>					
				</div>
	        </div> <!--  end container2 -->
        
	        <!--  table -->
	        <table border="1" class="table table-striped">        
				<tr>
					<th>Flight Number</th>
					<th>Airline</th>
					<th>Departure Travel Day</th>
					<th>Departure Travel Date</th>				
					<th>Source Airport</th>
					<th>Destination Airport</th>					
					<th>Action</th>			
				</tr>
				
				<!-- As a best practice, use JSTL Tags (instead of JSP Scriptlet Code). Makes the code more readable. -->		
				<c:forEach var="tempReturnResult" items="${RETURN_SEARCH_RESULTS_LIST}">					
					<!--  set up a link for each user using a JSTL feature called URL -->
					<!-- These 2 parameters are going to the Book Flight Controller Servlet -->
					<!-- We want to actually prepopulate the form on load on the booking details page. 
				     with that flight's information from the database.  -->
					<c:url var="tempLink" value="BookFlightControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="flightNumber" value="${tempReturnResult.flightNumber}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
						<c:param name="airline" value="${tempReturnResult.airlineName}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
						<c:param name="day" value="${tempReturnResult.day}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
						<c:param name="date" value="${returnDate}" />
						<c:param name="src" value="${tempReturnResult.sourceAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
						<c:param name="srcFullName" value="${tempReturnResult.sourceAiportFullName}" />
						<c:param name="dest" value="${tempReturnResult.destinationAirport}" /> <!-- Want to use the actual (embedded) userid, using JSP expression language -->
						<c:param name="destFullName" value="${tempReturnResult.destinationAiportFullName}" />						
					</c:url>
					<tr>
						<td>${tempReturnResult.flightNumber}</td><!-- Will actually call tempUser.getFlightNumber() due to JSP expression language -->
						<td>${tempReturnResult.airlineName}</td>
						<td>${tempReturnResult.day}</td>
						<td>${returnDate}</td>
						<td>${tempReturnResult.sourceAirport} | ${tempReturnResult.sourceAiportFullName}</td>
						<td>${tempReturnResult.destinationAirport} | ${tempReturnResult.destinationAiportFullName}</td>												
						<td><a href="${tempLink}" class="button">Book Tickets</a></td> <!-- So, this'll basically create an HREF with the text of Book Tickets, and then embedded is the actual tempLink that has the command and the flight number. -->
					</tr>				
				</c:forEach>				
			</table>
			<c:if test="${fn:length(RETURN_SEARCH_RESULTS_LIST) == 0}">
					<div class="row alert alert-light" role="alert">                
                	<h2>Your search did not yield any results</h2> <br><br>
            	</div><!-- end row -->
			</c:if>
		</c:if>
		<p>
			<a href="javascript:history.back()">Back to Search Page</a>
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