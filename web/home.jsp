<%-- 
    Document   : home
    Created on : Sep 15, 2020, 11:48:05 PM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home Page</title>
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
					    <a class="nav-link active" href="home">Home</a>
				    </li>
				    <c:if test="${account.roleID eq 'ADMIN'}">
					<li class="nav-item">
					    <a class="nav-link" href="manage-cake">Manage Cake</a>
					</li>
					<li class="nav-item">
					    <a class="nav-link" href="manage-user">Manage User</a>
					</li>
				    </c:if>
				    <c:if test="${account.roleID ne 'ADMIN'}">
					<li class="nav-item">
					    <a class="nav-link"href="track-order-page">Tracking Orders</a>
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
			<!-- Search form -->
			<c:set var="searchValue" value="${param.txtSearch}"/>
			<c:set var="searchPrice" value="${param.cmbPrice}"/>
			<c:set var="searchCategory" value="${param.cmbCategory}"/>
			<c:set var="categoryList" value="${requestScope.CATEGORY_LIST}"/>
			<div class= "my-4">
				<div class= "my-4">
				    <form action="search-cake" class="form-group"/> 
					    <input type="text" name="txtSearch" value="${searchValue}" class="form-control" placeholder="Search cakes..."/>
					    <label>Price Range ($)</label>
					    <select name="cmbPrice" class="form-control">
						<option></option>
						<option>0 - 5</option>
						<option>6 - 10</option>
						<option>11 - 15</option>
						<option>16 - 20</option>
						<option>21 - 25</option>
						<option>26 - 30</option>
						<option>>30</option>
					    </select> 
					    <label>Category</label>
					    <select name="cmbCategory" class="form-control">
						<option></option>
						<c:forEach var="item" items="${categoryList}">
						    <option value="${item.categoryID}"
							    <c:if test="${item.categoryID eq searchCategory}">
								selected="true"
							    </c:if>>
							${item.name}
						    </option>
						</c:forEach>
					    </select> 
					    <input type="hidden" name="page" value="1" />
					    <input type="hidden" name="destination" value="home" />
					    <div class="text-right">
						<input type="submit" value="Search" class="btn btn-success my-3" /> 
					    </div>
				</form> 
			</div>
			
			<!-- Display result -->
				<c:set var="result" value="${requestScope.CAKE_LIST}"/>
				<c:set var="totalPages" value="${requestScope.TOTAL_PAGES}"/>
				
				<c:if test="${not empty result}">
					<h3 class="text-center">CAKES</h3>
					<!-- Display cake -->
					    <div class="row justify-content-center">
						    <c:forEach var="item" items="${result}">	
							    <div class="col-12 col-md-6 col-lg-4 my-3">
								    <div class="card h-100">
									    <h5 class="card-header">
										    ${item.cake.name}
									    </h5>
									    <img src="load-image?file=${item.cake.image}" class="card-img-top"/>
									    <div class="card-body">
										<div>
										    ${item.cake.description}
										</div>
										<div>
										    <span  style="font-weight: bold">Category: </span>
										    ${item.category}
										</div>
										<div>
										    <span  style="font-weight: bold">Created Date: </span>
										    ${item.cake.createdDate}
										</div>
										<div>
										    <span  style="font-weight: bold">Expiration Date: </span>
										    ${item.cake.expirationDate}
										</div>
										<div>
										    <span  style="font-weight: bold">Price: </span>
										    <span style="font-weight: bold; color: red">${item.cake.price}$</span>
										</div>
										<c:if test="${account.roleID eq 'ADMIN'}">
										    <div>
											<span  style="font-weight: bold">Status: </span>
											${item.cake.statusID}
										    </div>
										</c:if>
									    </div>
									    <!-- Guest and member -->
									    <c:if test="${account.roleID ne 'ADMIN' or empty account}">
										<div class="card-footer">
										    <div class="text-right">
											<form action="add-to-cart">
											    <input type="hidden" name="cakeID" value="${item.cake.productID}"/>
											    <input type="hidden" name="txtSearch" value="${searchValue}"/>
											    <input type="hidden" name="cmbCategory" value="${searchCategory}"/>
											    <input type="hidden" name="cmbPrice" value="${searchPrice}"/>
											    <input type="hidden" name="page" value="${param.page}" />
											    <input type="hidden" name="destination" value="home"/>
											    <input type="submit" value="Add to cart" class="btn btn-success"/>
											</form>
										    </div>
										</div>
									    </c:if>
								    </div>
							    </div>
						    </c:forEach>
					    </div>
					
					
					<!-- get current page -->
					<c:set var="curPage" value="${requestScope.LOAD_ALL_DEFAULT_PAGE}"/>
					<c:if test="${empty curPage}">
					    <c:set var="curPage" value="${param.page}"/>
					</c:if>
					<!-- Page indicator -->
					<nav>
						<ul class="pagination justify-content-center mt-4">
							<c:forEach var="page" begin="1" end="${totalPages}" step="1">							
							    <c:set var="urlPaging" value="search-cake?txtSearch=${searchValue}
								   &cmbPrice=${searchPrice}&cmbCategory=${searchCategory}&page=${page}"/>
							    <c:if test="${curPage eq page}">
								    <li class="page-item active">
									    <a class="page-link" href="${urlPaging}">${page}</a>
								    </li>
							    </c:if>
							    <c:if test="${curPage ne page}">
								    <li class="page-item">
									    <a class="page-link" href="${urlPaging}">${page}</a>
								    </li>
							    </c:if>
							</c:forEach>
						</ul>
					</nav>
				</c:if>
				
				<c:if test="${empty result}">
					<h2 class="text-center">No cake found!!!</h2>
				</c:if>
		</div>
		<script src="assets/js/bootstrap.min.js"></script>
    </body>
</html>