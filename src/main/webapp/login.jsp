<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<!-- validate session -->
	<c:if test="${sessionScope.role != null}">
	    <c:redirect url="result.jsp" ></c:redirect>
	</c:if>
	
	<!-- Form area -->
	<div class="form">
		<div class="container">
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<h1 class="text-success text-center">Log-In Form</h1>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<form class="bg-light" action="LoginController" method="post">
						<span>${sessionScope.loginMsg}</span> <br>
						<br>
						<div class="form-group">
							<label>User Name</label> <input type="text" name="uname"
								class="form-control" autocomplete="off"> <span>${unmaeErr}</span>
						</div>

						<div class="form-group">
							<label>Password</label> <input type="password" name="password"
								class="form-control" autocomplete="off"> <span>${passwordErr}</span>
						</div>

						<span>${loginErr}</span>
						<div class="row">
							<div class="col-4">
								<button type="submit" name="Register" class="btn btn-success">
									Register
								</button>
							</div>
							<div class="col-4 text-right">
								<a href="forgotPassword.jsp" class="btn btn-link px-0">
									Forgot password?
								</a>
							</div>
						</div>

						<br>

						<div class="Register">
							<a href="register.jsp" class="btn btn-link px-0">
								Not Registered?Create an account 
							</a>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>