<%-- 
    Document   : view-cart
    Created on : Oct 10, 2020, 10:45:37 PM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/customize.css">
    </head>
    <body>
	<!-- Nav bar -->
	<c:set var="account" value="${sessionScope.LOGIN_USER}"/>
	<c:set var="cart" value="${sessionScope.CART}"/>
	<nav class="navbar navbar-dark navbar-expand-sm bg-warning">
		<c:if test="${not empty account}">
		    <ul class="navbar-nav mr-auto py-0">
			    <li class="nav-item">
				    <a class="nav-link" href="home">Home</a>
			    </li>
			    <li class="nav-item">
				<a class="nav-link"href="track-order-page">Tracking Orders</a>
			    </li>
			    <li class="nav-item">
				<a class="nav-link active" href="view-cart">
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
			<a class="nav-link active" href="view-cart">
			    Cart
			    <c:if test="${not empty cart.itemList}">
				<span class="badge badge-danger">${cart.totalItems}</span>
			    </c:if>
			    <c:if test="${empty cart.itemList}">
				<span class="badge badge-danger">0</span>
			    </c:if>
			</a>
		    </ul>
		    
		    <!-- Login form -->
		    <ul class="navbar-nav ml-auto text-center">
			    <li class="nav-item">
				<a class="btn btn-light btn-sm my-2 my-sm-0 mx-3" href="login-page">Login</a>
			    </li>
		    </ul>
		</c:if>
	</nav>
		
	<div class="container">
	    <div class="mt-3">
		<c:set var="cart" value="${requestScope.CART}"/>
		<c:if test="${not empty cart}">
		    <h1 class="text-center my-5">Your cart!</h1>
		    <c:set var="itemList" value="${cart.itemList}"/>
		    <table class="table">
			<thead>
			    <tr>
				<th scope="col">No.</th>
				<th scope="col">Cake Name</th>
				<th scope="col">Price</th>
				<th scope="col">Amount</th>
				<th scope="col">Total</th>
				<th scope="col">Update</th>
				<th scope="col">Delete</th>
			    </tr>
			</thead>
			<tbody>
			    <c:forEach var="item" items="${itemList}" varStatus="counter">
			    <form action="update-quantity">
				<input type="hidden" name="cakeID" value="${item.cake.productID}" />
				<tr>
				    <td scope="row">${counter.count}</td>
				    <td>${item.cake.name}</td>
				    <td>${item.cake.price}$</td>
				    <td>
					<input type="text" name="txtQuantity" value="${item.quantity}" class="form-control" style="width:100px !important" required/>
					<c:if test="${param.cakeID eq item.cake.productID}">
					    <c:set var="updateError" value="${requestScope.UPDATE_QUANTITY_ERROR}"/>
						<c:if test="${not empty updateError.quantityError}">
						    <div class="text-danger">
							${updateError.quantityError}
						    </div>
						</c:if>
					</c:if>
				    </td>
				    <td>${item.total}$</td>
				    <td>
					<input type="submit" value="Update" class="btn btn-outline-success"/>
				    </td>
				    <td>
					<a href="delete-from-cart?cakeID=${item.cake.productID}" class="btn btn-outline-danger" onclick="return confirm('Are you sure you want to remove ${item.cake.name}?')">Delete</a>
				    </td>
				</tr>
			    </form>
			    </c:forEach>
			    <c:set var="total" value="${requestScope.TOTAL_PRICE}"/>
			    <tr class="alert-primary" style="font-weight: bold">
				<td scope="row" colspan="6">Total Price:</td>
				<td scope="row">${total}$</td>
			    </tr>   
			</tbody>
		    </table>
		    <c:set var="checkoutError" value="${requestScope.CHECKOUT_ERROR}"/>
		    <c:if test="${not empty checkoutError.cakeIsNotAvailableError}">
			<div class="text-danger">
			    ${checkoutError.cakeIsNotAvailableError}
			</div>
		    </c:if>
		    <c:if test="${not empty checkoutError.buyQuantityError}">
			<div class="text-danger">
			    ${checkoutError.buyQuantityError}
			</div>
		    </c:if>
	
		    <!-- Checkout form -->
		    <c:set var="checkoutInfo" value="${sessionScope.CHECKOUT_INFO}"/>
		    <div class="my-5">
			<div class="card">		
				    <h5 class="card-header" style="font-weight: bold">Checkout</h5>
				    <div class="m-3">
					<div class="text-danger" style="font-weight: bold">
					    Please check your cart before check out to ensure everything is correct!
					</div>
					<form action="payment" method="GET" >
						<div class="form-group">
							<label>Name</label>
							<c:if test="${not empty account}">
							    <input type="text" name="txtCustomerName" value="${account.name}" placeholder="(eg 1 - 50 chars)" class="form-control" required/>
							</c:if>
							<c:if test="${empty account}">
							    <input type="text" name="txtCustomerName" value="${checkoutInfo.name}" placeholder="(eg 1 - 50 chars)" class="form-control" required/>
							</c:if>
							<c:if test="${not empty checkoutError.nameLengthError}">
							    <div class="text-danger">
								${checkoutError.nameLengthError}
							    </div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Address</label>
							<c:if test="${not empty account}">
							    <input type="text" name="txtAddress" value="${account.address}" placeholder="(eg 1 - 100 chars)" class="form-control" required/>
							</c:if>
							<c:if test="${empty account}">
							    <input type="text" name="txtAddress" value="${checkoutInfo.address}" placeholder="(eg 1 - 100 chars)" class="form-control" required/>
							</c:if>
							<c:if test="${not empty checkoutError.addressLengthError}">
							    <div class="text-danger">
								${checkoutError.addressLengthError}
							    </div>
							</c:if> 
						</div>
						<div class="form-group">
							<label>Phone number</label>
							<c:if test="${not empty account}">
							    <input type="text" name="txtPhone" value="${account.phoneNumber}" placeholder="up to 10 digits" class="form-control" required/>
							</c:if>
							<c:if test="${empty account}">
							    <input type="text" name="txtPhone" value="${checkoutInfo.phoneNumber}" placeholder="up to 10 digits" class="form-control" required/>
							</c:if>
							<c:if test="${not empty checkoutError.phoneNumberLengthError}">
							    <div class="text-danger">
								${checkoutError.phoneNumberLengthError}
							    </div>
							</c:if>
							<c:if test="${not empty checkoutError.phoneNumberFormarError}">
							    <div class="text-danger">
								${checkoutError.phoneNumberFormarError}
							    </div>
							</c:if>
						</div>
						
						<c:set var="paymentMethods" value="${requestScope.PAYMENT_METHODS}"/>
						<div class="form-group">
							<label>Payment Method</label>
							<select name="cmbPaymentMethod" class="form-control">
							    <c:forEach var="method" items="${paymentMethods}">
								<option value="${method.paymentID}"
									<c:if test="${(method.paymentID eq requestScope.cmbPaymentMethod) or (method.paymentID eq checkoutInfo.paymentMethod)}}">
									    selected="true"
									</c:if>>
								    ${method.paymentID}
								</option>
							    </c:forEach>			    
							</select> 
						</div>
						<c:if test="${not empty checkoutError.notPaidError}">
						    <div class="text-danger">
							${checkoutError.notPaidError}
						    </div>
						</c:if>
						<div class="text-right">
							<input type="submit" value="Confirm" class="btn btn-success mt-3" />
						</div>
					</form>	
				    </div>
			    </div>
		    </div>
		</c:if>
		<c:if test="${empty cart}">
		    <h1 class="text-center my-5">Your cart is empty!</h1>
		</c:if>
	    </div>
	</div>
	<script src="assets/js/bootstrap.min.js"></script>
    </body>
</html>
