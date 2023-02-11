<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Book Flight</title>
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
				<h2>Journey Details</h2>
			</div><!-- end row -->
			
			<!--  Row 2 -->
            <div class="row"> 
				<form action="BookFlightControllerServlet" method="get">
				<input type="hidden" name="command" value="SEARCH" />
									
					<input type="radio" onclick="javascript:directionCheck();" name="flightDirection" id="rt" value="roundtrip" checked="checked">Round Trip
					<input type="radio" onclick="javascript:directionCheck();" name="flightDirection" id="oneway" value="oneway">One Way <br>
										
					<label for="departureCity"><strong>Departure City</strong></label>
					<select name="departureCity">
						<c:forEach var="tempCity" items="${CITIES_LIST}">
							<option>${tempCity.airportCode} | ${tempCity.airportName}</option>							
						</c:forEach>
					</select>					

					<label for="arrivalCity"><strong>Arrival City</strong></label>
					<select name="arrivalCity">
						<c:forEach var="tempCity" items="${CITIES_LIST}">
							<option>${tempCity.airportCode} | ${tempCity.airportName}</option>							
						</c:forEach>
					</select>
					<br>
					
					<label for="departDate"><strong>Depart Date:</strong></label>
					<input type="date" id="departDate" name="departDate">
					
					<div id="ifRT" style="visibility:visible">
						<label for="returnDate"><strong>Return Date:</strong></label>
						<input type="date" id="returnDate" name="returnDate"><br><br>
					</div>
					
					<label for="numTravelers"><strong>Number of Travelers</strong></label>					
					<select name="numOfTravelers">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>					
					</select><br><br>	
										
					<button type="submit">Search Flights</button>								 
		
				</form>	
			</div><!-- end row --> 
        </div> <!--  end container2 --> 
	</main>
	<footer id="page_footer">
        <!-- Footer, placed at the end of a web page -->
        <p>&copy;2022-2023 FlyAway, Inc.</p>    
    </footer>
    
    <script>
    	function directionCheck(){
    		if(document.getElementById('oneway').checked){
    			document.getElementById('ifRT').style.visibility = 'hidden';
    			//document.getElementById('ifRT2').style.visibility = 'hidden';
    		}
    		else{
    			document.getElementById('ifRT').style.visibility = 'visible';
    			//document.getElementById('ifRT2').style.visibility = 'visible';
    		}
    	}
    </script>    
</body>
</html>