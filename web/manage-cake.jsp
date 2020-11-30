<%-- 
    Document   : manage-cake
    Created on : Oct 9, 2020, 11:22:50 PM
    Author     : Long Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>Manage Cake Page</title>
	    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
	    <link rel="stylesheet" href="assets/css/customize.css">
    </head>
    <body>
		<!-- Nav bar -->
		<c:set var="account" value="${sessionScope.LOGIN_USER}"/>
		<nav class="navbar navbar-dark navbar-expand-sm bg-warning">
			<c:if test="${not empty account}">
			    <ul class="navbar-nav mr-auto py-0">
				    <li class="nav-item">
					    <a class="nav-link"href="home">Home</a>
				    </li>
				    <c:if test="${account.roleID eq 'ADMIN'}">
					<li class="nav-item">
					    <a class="nav-link active"href="manage-cake">Manage Cake</a>
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
		</nav>
	
		<div class="container">
			<!-- Search form -->
			<c:set var="searchValue" value="${param.txtSearch}"/>
			<c:set var="searchPrice" value="${param.cmbPrice}"/>
			<c:set var="searchCategory" value="${param.cmbCategory}"/>
			<c:set var="categoryList" value="${requestScope.CATEGORY_LIST}"/>
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
					<input type="hidden" name="destination" value="manage-cake" />
					<div class="text-right">
					    <input type="submit" value="Search" class="btn btn-success my-3" /> 
					</div>
				</form> 
			</div>
			
			<!-- Create cake form -->
			<c:if test="${account.roleID eq 'ADMIN'}">
			    <c:set var="errors" value="${requestScope.CREATE_CAKE_ERRORS}"/>
			    <div class="card">
				    <h3 class="card-header" style="font-weight: bold">Create a new cake</h3>
				    <div class="m-3">
					    <form action="create-cake" method="POST" enctype="multipart/form-data" >
						    <div class="form-group">
							    <label>Name</label>
							    <input type="text" name="txtName" value="" placeholder="(eg 1 - 50 chars)" class="form-control" required/>
							    <c:if test="${not empty errors.nameLengthError}">
								<div class="text-danger">${errors.nameLengthError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Description</label>
							    <textarea name="txtDescription" rows="5" cols="50" placeholder="(eg 1 - 200 chars)" class="form-control" required></textarea>
							    <c:if test="${not empty errors.descriptionLengthError}">
								<div class="text-danger">${errors.descriptionLengthError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Created Date</label>
							    <input type="text" name="txtCreatedDate" value="" placeholder="yyyy-MM-dd" class="form-control" required/>
							    <c:if test="${not empty errors.createdDateFormatError}">
								<div class="text-danger">${errors.createdDateFormatError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Expiration Date</label>
							    <input type="text" name="txtExpiration" value="" placeholder="yyyy-MM-dd" class="form-control" required/>
							    <c:if test="${not empty errors.expirationDateFormatError}">
								<div class="text-danger">${errors.expirationDateFormatError}</div>
							    </c:if>
							    <c:if test="${not empty errors.expirationDateError}">
								<div class="text-danger">${errors.expirationDateError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Price</label>
							    <input type="text" name="txtPrice" placeholder="$" class="form-control" required/>    
							    <c:if test="${not empty errors.priceError}">
								<div class="text-danger">${errors.priceError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Quantity</label>
							    <input type="text" name="txtQuantity" value="1" class="form-control" required/>  
							    <c:if test="${not empty errors.quantityError}">
								<div class="text-danger">${errors.quantityError}</div>
							    </c:if>
						    </div>
						    <div class="form-group">
							    <label>Category</label>
							    <select name="cmbCategory" class="form-control">
								<c:forEach var="item" items="${categoryList}">
								    <option value="${item.categoryID}">${item.name}</option>
								</c:forEach>
							    </select>   
						    </div>
						    <div class="form-group">
							    <label>Choose an image</label>
							    <input type="file" accept="image/*" name="image" class="form-control-file" required/>
						    </div>
						    <div class="text-right">
							    <input type="submit" value="Create Cake" class="btn btn-success mt-3" />
						    </div>
					    </form>	
				    </div>
			    </div>
			    <br/><br/>
			</c:if>
			
			
			<!-- Display result -->
				<c:set var="result" value="${requestScope.CAKE_LIST}"/>
				<c:set var="totalPages" value="${requestScope.TOTAL_PAGES}"/>
				
				<c:if test="${not empty result}">
					<h3 class="text-center">CAKES</h3>
					<!-- Display cake -->
					<!-- Admin -->
					<c:if test="${account.roleID eq 'ADMIN'}">
					    <c:set var="updateErrors" value="${requestScope.UPDATE_CAKE_ERRORS}"/>
					    <div class="row justify-content-center">
						    <c:forEach var="item" items="${result}">	
							    <div class="col-md-6 my-3">
								    <div class="card h-100">
									    <img src="load-image?file=${item.cake.image}" class="card-img-top"/>
									    <form action="update-cake" method="POST" enctype="multipart/form-data" >
										<div class="card-body">
										    <div class="form-group">
											    <label>Name</label>
											    <input type="text" name="txtName" value="${item.cake.name}" placeholder="(eg 1 - 50 chars)" class="form-control" required/>
											    <c:if test="${not empty updateErrors.nameLengthError}">
												<div class="text-danger">${updateErrors.nameLengthError}</div>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Description</label>
											    <textarea name="txtDescription" rows="5" cols="50" placeholder="(eg 1 - 200 chars)" class="form-control" required>${item.cake.description}</textarea>
											    <c:if test="${param.cakeID eq item.cake.productID}">
												<c:if test="${not empty updateErrors.descriptionLengthError}">
												    <div class="text-danger">${updateErrors.descriptionLengthError}</div>
												</c:if>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Created Date</label>
											    <input type="text" name="txtCreatedDate" value="${item.cake.createdDate}" placeholder="yyyy-MM-dd" class="form-control" required/>
											    <c:if test="${param.cakeID eq item.cake.productID}">
												<c:if test="${not empty updateErrors.createdDateFormatError}">
												    <div class="text-danger">${updateErrors.createdDateFormatError}</div>
												</c:if>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Expiration Date</label>
											    <input type="text" name="txtExpiration" value="${item.cake.expirationDate}" placeholder="yyyy-MM-dd" class="form-control" required/>
											    <c:if test="${param.cakeID eq item.cake.productID}">
												<c:if test="${not empty updateErrors.expirationDateFormatError}">
												    <div class="text-danger">${updateErrors.expirationDateFormatError}</div>
												</c:if>
												<c:if test="${not empty updateErrors.expirationDateError}">
												   <<div class="text-danger">${updateErrors.expirationDateError}</div>
												</c:if>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Price</label>
											    <input type="text" name="txtPrice" value="${item.cake.price}" placeholder="$" class="form-control" required/>    
											    <c:if test="${param.cakeID eq item.cake.productID}">
												<c:if test="${not empty updateErrors.priceError}">
												    <div class="text-danger">${updateErrors.priceError}</div>
												</c:if>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Quantity</label>
											    <input type="text" name="txtQuantity" value="${item.cake.quantity}" class="form-control" required/>  
											    <c:if test="${param.cakeID eq item.cake.productID}">
												<c:if test="${not empty updateErrors.quantityError}">
												    <div class="text-danger">${updateErrors.quantityError}</div>
												</c:if>
											    </c:if>
										    </div>
										    <div class="form-group">
											    <label>Category</label>
											    <select name="cmbCategory" class="form-control">
												<c:forEach var="category" items="${categoryList}">
												    <option value="${category.categoryID}"
													    <c:if test="${item.cake.categoryID eq category.categoryID}">
														selected="true"
													    </c:if>>
													${category.name}
												    </option>
												</c:forEach>
											    </select>   
										    </div>
										    <div class="form-group">
											    <c:set var="statusList" value="${requestScope.STATUS_LIST}"/>
											    <label>Status</label>
											    <select name="cmbStatus" class="form-control">
												<c:forEach var="status" items="${statusList}">
												    <option value="${status.statusID}"
													    <c:if test="${item.cake.statusID eq status.statusID}">
														selected="true"
													    </c:if>>
													${status.status}
												    </option>
												</c:forEach>
											    </select>   
										    </div>
										    <div class="form-group">
											    <label>Choose an image</label>
											    <input type="file" accept="image/*" name="image" class="form-control-file"/>
										    </div>
										</div>
										    <input type="hidden" name="cakeID" value="${item.cake.productID}" />
										    <input type="hidden" name="oldImageLink" value="${item.cake.image}" />
										<div class="text-right card-footer">
											<input type="submit" value="Update Cake" class="btn btn-success mt-3" />
										</div>
									    </form>						
									    
								    </div>
							    </div>
						    </c:forEach>
					    </div>
					</c:if>
					
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