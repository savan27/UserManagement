<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" errorPage="error.jsp"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"> -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<!-- validate session -->
	<c:if test="${sessionScope.role != null}">
	    <!-- including header  -->
		<%@ include file="header.jsp"%>
	</c:if>
	
</head>
<body>

	<!-- prevent brouser back button after session expires -->
	<%
	  response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	%>
	
	<!-- setting user data -->
	<c:set var="user" scope="session" value="${sessionScope.userData}" />
	<c:set var="address" scope="session" value="${sessionScope.addressData}" />
	
	<!-- Form area starts-->
	<div class="form" >
		<div class="container">
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<h1 class="text-success text-center">
					${empty sessionScope.operation ? 'Registration Form' : 'Update User Profile'} 
					</h1>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<form action="RegisterController" method="post" class="bg-light"  id="Form" enctype="multipart/form-data" >
						
						<div class="form-group">
							<br>
							<div class="circle">
								<img class="profile-pic" name="defaultImage" src="data:image/gif;base64,${user.displayImage}" />
								<input type="hidden" name="defaultImageValue" value="${user.displayImage}" />
							</div>
							<label>Upload Profile Picture </label>
							<i class="fa fa-camera upload-button"></i>
							<div class="p-image">
								<input class="file-upload" name="image_file" type="file" accept="image/*" id="File" onchange="imageValidate(this)" />
								<span id="FileError" class="text-danger font-weight-bold"></span>
							</div>
						</div>
						
						<div class="form-group">
							<label>First Name</label>
							<input type="text" name="firstNname" class="form-control" id="firstName1" autocomplete="off" onblur="FnameValidate(1)" value="${empty sessionScope.firstName ? user.firstName : firstName}">
							<span id="FnameError1" class="text-danger font-weight-bold"></span>
							<span >${fnameErr}</span>
						</div>

						<div class="form-group">	
							<label>Last Name</label>
							<input type="text" name="lastNname" class="form-control" id="lastName1" autocomplete="off" onblur="LnameValidate(1)" value="${empty sessionScope.lastName ? user.lastName : lastName}">
							<span id="LnameError1" class="text-danger font-weight-bold"></span>
							<span >${lnameErr}</span>
						</div>

						<div class="form-group">
							<label>Password</label>
							<input type="password" name="password" class="form-control" id="password1" autocomplete="off"  onpaste="return false;" onblur="pwdValidate(1)" value="${user.password}">
							<span id="PasswordError1" class="text-danger font-weight-bold"></span>
							<span >${passwordErr}</span>
						</div>

						<div class="form-group">
							<label>Confirm Password</label>
							<input type="password" name="Cpassword" class="form-control" id="confirmPassword1" autocomplete="off"  onpaste="return false;" onblur="cpwdValidate(1)" value="${user.password}">
							<span id="CpasswordError1" class="text-danger font-weight-bold"></span>
							<span >${confPasswordErr}</span>
						</div>

						<div class="form-group">
							<label>Email Address</label>
							<input type="text" name="email" class="form-control" id="Email1" autocomplete="off" onblur="emailValidate(1)" value="${empty sessionScope.email ? user.email : email}">
							<span id="EmailError1" class="text-danger font-weight-bold"></span>
							<span >${emailErr}</span>
						</div>

						<div class="form-group">
							<label>Contact No.</label>
							<input type="text" name="contact" class="form-control" id="Contact1" autocomplete="off" onblur="conValidate(1)" value="${empty sessionScope.contact ? user.contact : contact}">
							<span id="ContactError1" class="text-danger font-weight-bold"></span>
							<span >${contactErr}</span>
						</div>

						<div class="form-group"> 
							<label>Gender</label>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender" id="male" value="male" ${user.gender == 'male' ? 'checked' : '' }>
								<label class="form-check-label" >
									Male
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender" id="female" value="female" ${user.gender == 'female' ? 'checked' : '' }>
								<label class="form-check-label" >
									Female
								</label>
							</div>
						</div>

						<div class="form-group">
							<label>Select Permission</label><br>
							<input type="checkbox" name="permission" value="Read" ${fn:contains(user.permission,'Read') ? 'checked' : ''}>	Read
							<input type="checkbox" name="permission" value="Write" ${fn:contains(user.permission,'Write') ? 'checked' : ''}>Write
							<input type="checkbox" name="permission" value="Execute" ${fn:contains(user.permission,'Execute') ? 'checked' : ''}>Execute
						</div>

						<div class="form-group">
							<label>Select You Like</label><br>
							<select name="hobby" id="t">
							    <option value="Read" ${user.hobbies=='Read' ? 'selected' : ''}>Read</option>
							    <option value="Write" ${user.hobbies=='Write' ? 'selected' : ''}>Write</option>
							    <option value="Executeid" ${user.hobbies=='Execute' ? 'selected' : ''}>Execute</option>
						    </select>
						</div>

					<!-- manage address for existing user or new one -->
						<c:choose>
							<c:when test="${empty address}">
							<c:set var="count" value="2" scope="page" />
								<div>
									<div class="form-group">
										<label>Address 1:</label>
										<span style="float: right;" id="addAddress1"><i class="fa fa-plus"></i></span>
										<pre style="float: right;"> | </pre>
										<span style="float: right;" id="removeAddress1"><i class="fa fa-minus"></i></span>
										<input type="text" name="home" class="form-control" id="house1" autocomplete="off" placeholder="Address Line1" onblur="HomeValidate(1)" value="${sessionScope.home}">
										<span id="HouseError1" class="text-danger font-weight-bold"></span>
										<span>${address1Err}</span>
									</div>						
			
									<div class="form-group">
										<input type="text" name="leandmark" class="form-control" id="landmark1" autocomplete="off" placeholder="Address Line2" onblur="StreetValidate(1)" value="${sessionScope.leandmark}">
										<span id="Landmark2Error1" class="text-danger font-weight-bold"></span>
										<span >${address2Err}</span>
									</div>
									
									<div class="row">
										<div class="col-md-6">
											<input type="text" name="City" class="form-control" id="City1" autocomplete="off" placeholder="City" onblur="CityValidate(1)" value="${sessionScope.city}">
											<span id="CityError1" class="text-danger font-weight-bold"></span>
											<span >${cityErr}</span>
										</div>
										<div class="col-md-6">
											<input type="text" name="State" class="form-control" id="State1" autocomplete="off" placeholder="State" onblur="StateValidate(1)" value="${sessionScope.state}" >
											<span id="StateError1" class="text-danger font-weight-bold"></span>
											<span >${stateErr}</span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-6">
											<input type="text" name="Country" class="form-control" id="Country1" autocomplete="off" placeholder="Country" onblur="CountryValidate(1)" value="${sessionScope.country}">
											<span id="CountryError1" class="text-danger font-weight-bold"></span>
											<span>${countryErr}</span>
										</div>
										<div class="col-md-6">
											<input type="text" name="ZipCode" class="form-control" id="ZipCode1" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(1)" value="${sessionScope.zipCode}">
											<span id="ZipCodeError1" class="text-danger font-weight-bold"></span>
											<span >${zipCodeErr}</span>
										</div>
									</div>
									<input type="hidden" name="addressId" value="newAdd" />
								</div>	
								<!-- get the last count -->
								<input type="hidden" id="addressCount" value="${count}">
						    </c:when> 
						       
						    <c:otherwise>
						    	<c:set var="count" value="1" scope="page" />
								<c:forEach items="${address.addressList}" var="data">
									<div>
										<div class="form-group">
											<label>Address ${count}:</label>
											<span style="float: right;" id="addAddress${data.AddressId}"><i class="fa fa-plus"></i></span>
											<pre style="float: right;"> | </pre>
											<span style="float: right;" id="removeAddress${data.AddressId}"><i class="fa fa-minus"></i></span>
											<input type="text" name="home" class="form-control" id="house${count}" autocomplete="off" placeholder="Address Line1" onblur="HomeValidate(${count})" value="${data.home}">
											<span id="HouseError${count}" class="text-danger font-weight-bold"></span>
											<span>${address1Err}</span>
										</div>						
				
										<div class="form-group">
											<input type="text" name="leandmark" class="form-control" id="landmark${count}" autocomplete="off" placeholder="Address Line2" onblur="StreetValidate(${count})" value="${data.landmark}">
											<span id="Landmark2Error${count}" class="text-danger font-weight-bold"></span>
											<span >${address2Err}</span>
										</div>
										
										<div class="row">
											<div class="col-md-6">
												<input type="text" name="City" class="form-control" id="City${count}" autocomplete="off" placeholder="City" onblur="CityValidate(${count})" value="${data.city}">
												<span id="CityError${count}" class="text-danger font-weight-bold"></span>
												<span >${cityErr}</span>
											</div>
											<div class="col-md-6">
												<input type="text" name="State" class="form-control" id="State${count}" autocomplete="off" placeholder="State" onblur="StateValidate(${count})" value="${data.state}">
												<span id="StateError${count}" class="text-danger font-weight-bold"></span>
												<span >${stateErr}</span>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-md-6">
												<input type="text" name="Country" class="form-control" id="Country${count}" autocomplete="off" placeholder="Country" onblur="CountryValidate(${count})" value="${data.country}">
												<span id="CountryError${count}" class="text-danger font-weight-bold"></span>
												<span>${countryErr}</span>
											</div>
											<div class="col-md-6">
												<input type="text" name="ZipCode" class="form-control" id="ZipCode${count}" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(${count})" value="${data.zipcode}">
												<span id="ZipCodeError${count}" class="text-danger font-weight-bold"></span>
												<span >${zipCodeErr}</span>
											</div>
										</div>
										<input type="hidden" name="addressId" value="${data.AddressId}" />
										<br>
									</div>
									<!-- increment the count -->
									<c:set var="count" value="${count + 1}" scope="page"/>
								</c:forEach>
								<!-- get the last count -->
								<input type="hidden" id="addressCount" value="${count}">
						    </c:otherwise>
						</c:choose>
						
						
						<!-- New address Append Here -->
						<div class="new_address_wrap">
							
						</div>
						
						<br>	
						
						<!-- hidden filds to manage data data flow -->
						<input type="hidden" name="uid" value="${sessionScope.userId}" />
						<input type="hidden" name="userRole" value="${sessionScope.userRole}" />
						
						<!-- Store the address count for validatio -->
						<div id="countadd" style="display:none; "></div>
						
						<span style="color:red" id="errMassage" class="text-danger font-weight-bold">${sessionScope.errMassage}</span><br>
						<button type="submit" name="operation" value="<c:out value="${empty sessionScope.operation ? 'Register' : operation}" />" class="btn btn-success" form="Form" id="btn-submit" onclick="return validation(1);">
							<c:out value="${empty sessionScope.operation ? 'Register' : operation}" />
						</button>
						<button class="btn btn-danger" name="operation" value="cancel">
							Cancel
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Form area ends-->

	<!-- Script -->
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
	<script type="text/javascript" src="js/valid.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/appendAddress.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	
	<!-- validate session -->
	<c:if test="${sessionScope.role != null}">
	    <!-- including footer -->
		<%@ include file="footer.jsp"%>
	</c:if>
	
</body>
</html>