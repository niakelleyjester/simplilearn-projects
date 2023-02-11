<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Payment</title>
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
                <h2>Payment Form</h2> <br><br>
            </div><!-- end row -->
            
            <!--  Row 2 -->
            <div class="row">               	
               	<form action="PaymentServlet" method="post">
               		<input type="hidden" name="command" value="PAY" />
               		<label for="customerName"><strong>CardHolder Name</strong></label>
    				<input type="text" value="${sessionScope.loggedInUser.firstName} ${sessionScope.loggedInUser.lastName}" name="customerName" required><br>
    				               		
                   	<label for="cardNumber"><strong>Card Number</strong></label>
    				<input type="text" placeholder="Enter Card Number" name="cardNumber" required><br>
    				
    				<label for="totalCost"><strong>Total Amount to be Paid</strong></label>
    				<input type="text" name="totalCost" value="$${savedTicket.totalFare}" readonly /><br>
    				
    				<c:url var="tempLink" value="PaymentServlet">
					<c:param name="command" value="PAYMENT_SUCCESS" />
					</c:url>
					<a href="${tempLink}" class="button">Confirm Payment</a>    				                   	              	
                    <!--  <a href="PaymentServlet" type="submit">Confirm Payment</button> -->
                    <a href="javascript:history.back()">Back to Booking Details</a>
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