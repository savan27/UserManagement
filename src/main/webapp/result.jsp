<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Page</title>

<!-- including header  -->
<%@ include file="header.jsp"%>


</head>
<body>

	<!-- prevent brouser back button after session expires -->
	<%
	  response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	%>

	<!-- validate session -->
	<c:if test="${sessionScope.role == null}">
	    <c:redirect url="login.jsp" ></c:redirect>
	</c:if>


	<!-- Admin profile section starts -->
	<c:if test="${sessionScope.role == 'admin'}">

		<!-- css -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap4.min.css" >

		<div class="container">
			<table id="example" class="table table-striped " style="width:100%;">
				<thead>
					<tr>
						<th>User ID</th>
						<th>First Name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Contact</th>
						<th>Profile</th>
						<th>Gender</th>
						<th>Permission</th>
						<th>Hobbies</th>
						<th>Manage User</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.userList}" var="user">
						<tr>
							<td><c:out value="${user.userRId}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.contact}" /></td>
							<td><img src="data:image/gif;base64,${user.displayImage}" alt="profilePhoto" width="70px" height="70px" /></td>
							<td><c:out value="${user.gender}" /></td>
							<td><c:out value="${user.permission}" /></td>
							<td><c:out value="${user.hobbies}" /></td>
							<td>
								<a href="RegisterController?role=admin&operaton=Update&id=<c:out value='${user.userRId}' />" style="color:white">
									<button class = "btn btn-sm btn-success btn">
										Edit
									</button>
								</a> 
								| 
								<button id = "delbtn" value = '${user.userRId}' class = "btn btn-sm btn-danger btn"> Delete</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Script -->
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/popper.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>
		<script type="text/javascript" src="js/dataTable.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/valid.js"></script>

	</c:if>
	<!-- Admin profile section ends -->
	
<!-- ---------------------------------------------------------------------------------------------------------- -->

	<!-- User profile section starts -->
	<c:if test="${sessionScope.role == 'user' }">
	
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/userprofile.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">

		<!-- setting user data -->
		<c:set var="user" scope="session" value="${sessionScope.userData}" />
		<c:set var="address" scope="session" value="${sessionScope.addressData}" />

		<!-- User data display starts -->
		<div class="container emp-profile">
			<form method="post">
				<div class="row">
					<div class="col-md-4">
						<div class="profile-img">
							<img src="data:image/gif;base64,${user.displayImage}"
								alt="profilePhoto" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="profile-head">
							<h5>
								<c:out value="${user.firstName}"></c:out>
							</h5>
							<br>
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="home-tab" data-toggle="tab" href="#home" role="tab"
									aria-controls="home" aria-selected="true">About</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-2">
						<a href="RegisterController?role=user&operaton=Update&id=<c:out value='${user.userRId}' />">
						<button type="button" class="profile-edit-btn" name="btnAddMore">
							Edit Profile
						</button></a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<!-- Add something to show under the profile picture -->
						<span id="errorMassage" ></span>
					</div>
					<div class="col-md-8">
						<div class="tab-content profile-tab" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel"
								aria-labelledby="home-tab">
								<div class="row">
									<div class="col-md-6">
										<label>First Name</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.firstName}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Last Name</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.lastName}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Email</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.email}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Phone</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.contact}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Gender</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.gender}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Permission</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.permission}"></c:out>
										</p>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Hobby</label>
									</div>
									<div class="col-md-6">
										<p>
											<c:out value="${user.hobbies}"></c:out>
										</p>
									</div>
								</div>
								<br>
									
								<!-- to manage address number -->
								<c:set var="count" value="1" scope="page" />
								<c:forEach items="${address.addressList}" var="data">
									<div class="row">
										<div class="col-md-6">
											<label>Address ${count}:</label>
											<input type="hidden" name="addressId" value="${data.AddressId}" />
										</div>
										<div class="col-md-6">
											<div class="row">
												<div class="col-md-4">
													<label>House</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.home}"></c:out>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<label>Locality</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.landmark}"></c:out>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<label>City</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.city}"></c:out>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<label>State</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.state}"></c:out>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<label>Country</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.country}"></c:out>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<label>ZipCode</label>
												</div>
												<div class="col-md-8">
													<p>
														<c:out value="${data.zipcode}"></c:out>
													</p>
												</div>
											</div>
										</div>
									</div>
									<hr>
									<c:set var="count" value="${count + 1}" scope="page"/>
								</c:forEach> 
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- script -->
		<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="js/popper.min.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/valid.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		

	</c:if>
	<!-- User profile section ends -->

	<!-- including footer -->
	<%@ include file="footer.jsp"%>

</body>
</html>