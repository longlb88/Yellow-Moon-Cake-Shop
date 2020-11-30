<%-- 
    Document   : checkout-success
    Created on : Oct 12, 2020, 9:43:25 PM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Successfully</title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/customize.css">
    </head>
    <body>
        <c:set var="account" value="${sessionScope.LOGIN_USER}"/>
	<nav class="navbar navbar-dark navbar-expand-sm bg-warning">
		<c:if test="${not empty account}">
		    <ul class="navbar-nav mr-auto py-0">
			    <li class="nav-item">
				    <a class="nav-link" href="home">Home</a>
			    </li>
			    <li class="nav-item">
				    <a class="nav-link"href="tracking-order">Tracking Orders</a>
			    </li>
			    <li class="nav-item">
				<a class="nav-link" href="view-cart">
				    Cart
				    <c:if test="${not empty cart.itemList}">
					<span class="badge badge-danger">${cart.totalItems}</span>
				    </c:if>
				    <c:if test="${empty cart.itemList}">
					<span class="badge badge-danger">0</span>
				    </c:if>
				</a>
			    </li>
		    </ul>
		    <ul class="navbar-nav ml-auto text-center">
			    <!-- Logout form -->
			    <li class="nav-item">
				    <div class="welcome-user">Welcome, ${account.name}</div>		
			    </li>
			    <li class="nav-item">
				    <form action="logout">
					    <input type="submit" value="Logout" class="btn btn-danger btn-sm my-2 my-sm-0 mx-3"/>
				    </form>
			    </li>
		    </ul>
		</c:if>

		<c:if test="${empty account}">
		    <ul class="navbar-nav mr-auto py-0">
			<li class="nav-item">
				<a class="nav-link" href="home">Home</a>
			</li>			    
			<li class="nav-item">
				<a class="nav-link" href="view-cart">
				    Cart
				    <c:if test="${not empty cart.itemList}">
					<span class="badge badge-danger">${cart.totalItems}</span>
				    </c:if>
				    <c:if test="${empty cart.itemList}">
					<span class="badge badge-danger">0</span>
				    </c:if>
				</a>
			</li>
		    </ul>
		    
		    <!-- Login form -->
		    <ul class="navbar-nav ml-auto text-center">
			    <li class="nav-item">
				<a class="btn btn-light btn-sm my-2 my-sm-0 mx-3" href="login-page">Login</a>
			    </li>
		    </ul>
		</c:if>
	    </nav>
		
		<div class="container-fluid h-100">
			<div class="row h-100 justify-content-center align-items-center">
				<div class="col-10 col-md-8 col-lg-6 text-center">
					<img src="assets/images/success.png" alt="success" width="200" height="200"> <br/><br/>
					<h1>Checkout your cart successfully!</h1><br/>
					<c:if test="${not empty account}">
					    <c:set var="orderID" value="${requestScope.ORDER_ID}"/>
					    <h4>You can check out your cart using the ID below</h4>
					    <div class="text-danger" style="font-weight: bold">
						${orderID}
					    </div>
					</c:if>
				</div>
			</div>
		</div>
	<script src="assets/js/bootstrap.min.js"></script>
    </body>
</html>
