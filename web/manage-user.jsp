<%-- 
    Document   : manage-user
    Created on : Oct 29, 2020, 12:53:24 PM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Page</title>
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
				    <c:if test="${account.roleID eq 'ADMIN'}">
					<li class="nav-item">
					    <a class="nav-link" href="manage-cake">Manage Cake</a>
					</li>
					<li class="nav-item">
					    <a class="nav-link active" href="manage-user">Manage User</a>
					</li>
				    </c:if>
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
					<a class="nav-link active" href="home">Home</a>
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
				    <li class="nav-item">
					<a class="btn btn-light btn-sm my-2 my-sm-0 mx-3" href="login-page">Login</a>
				    </li>
			    </ul>
			</c:if>
		</nav>
		
	<div class="container">
	<div class="mt-3">
	<h1 class="text-center">User List</h1>
	<c:set var="userList" value="${requestScope.USER_LIST}"/>
		<c:if test="${not empty userList}">
		    <table class="table">
			<thead>
			    <tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Address</th>
				<th scope="col">Phone</th>
				<th scope="col">Role</th>
				<th scope="col">Status</th>
			    </tr>
			</thead>
			<tbody>
			    <c:forEach var="user" items="${userList}" varStatus="counter">
				<tr>
				    <td>${user.userID}</td>
				    <td>${user.name}</td>
				    <td>${user.address}</td>
				    <td>${user.phoneNumber}</td>
				    <td>${user.roleID}</td> 
				    <td>${user.statusID}</td>
				</tr>
			    </c:forEach>
			</tbody>
		    </table>
		</c:if>
		</div>
	</div>
    </body>
</html>
