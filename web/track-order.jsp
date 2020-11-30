<%-- 
    Document   : track-order
    Created on : Oct 13, 2020, 11:00:31 AM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Track Order</title>
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
				    <a class="nav-link active" href="#">Tracking Orders</a>
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
	</nav>
	
	<div class="container">
	    <!-- Search form -->
	    <c:set var="searchValue" value="${param.txtSearchOrder}"/>
	    <div class= "my-4">
		    <form action="track-order" class="form-inline"/> 
			    <input type="text" name="txtSearchOrder" value="${searchValue}" class="form-control col-10" placeholder="Enter OrderID"/>
			    <input type="submit" value="Search" class="btn btn-success col-2" /> 
		    </form> 
	    </div>
	
	    <c:if test="${not empty searchValue}">
		<c:set var="orderInfo" value="${requestScope.ORDER}"/>
		<c:if test="${not empty orderInfo}">
		    <c:set var="detailList" value="${requestScope.ORDER_DETAILS}"/>
		    <div class="card">
			<h5 class="card-header" style="font-weight: bold">Order ID: ${searchValue}</h5>
			<div class="card-body">
			    <div>
				<span style="font-weight: bold">Username: </span>${orderInfo.name}
			    </div>
			    <div>
				<span style="font-weight: bold">Address: </span>${orderInfo.address}
			    </div>
			    <div>
				<span style="font-weight: bold">Phone: </span>${orderInfo.phoneNumber}
			    </div>
			    <div>
				<span style="font-weight: bold">Order Date: </span>${orderInfo.orderDate}
			    </div>
			    <div>
				<span style="font-weight: bold">Payment Method: </span>${orderInfo.paymentID}
			    </div>
			    <div>
				<span style="font-weight: bold">Payment Status: </span>${orderInfo.paymentStatus}
			    </div>
			</div>
		    </div>

		    <table class="table">
		       <thead>
			   <tr>
			       <th scope="col">No.</th>
			       <th scope="col">Cake Name</th>
			       <th scope="col">Amount</th>
			       <th scope="col">Total</th>
			   </tr>
		       </thead>
		       <tbody>
			   <c:forEach var="item" items="${detailList}" varStatus="counter">
			       <tr>
				   <td scope="row">${counter.count}</td>
				   <td>${item.cakeName}</td>
				   <td>${item.orderDetail.quantity}</td>
				   <td>${item.orderDetail.totalPrice}$</td>
			       </tr>
			   </c:forEach>
			   <tr class="alert-primary" style="font-weight: bold">
			       <td scope="row" colspan="3">Total Price:</td>
			       <td scope="row">${orderInfo.total}$</td>
			   </tr>
		       </tbody>
		   </table>
		</c:if>
		<c:if test="${empty orderInfo}">
		    <h3 class="text-center">
			No order found!
		    </h3>
		</c:if>
	    </c:if>	 
	</div>
	<script src="assets/js/bootstrap.min.js"/>
    </body>
</html>
