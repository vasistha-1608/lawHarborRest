<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">

<title>Admin Reg Form</title>
<style type="text/css">
.error {
	font-size: small;
	font-weight: 300;
	color: red;
	font-style: inherit;
}
</style>
</head>
<body>

	<div class="container mt-5">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-body">

					<h1 class="text-center">Admin Registration Form</h1>

					<form action="/Admin/AdminRegister/${AdminEmail}" method="post">
						<div class="form-group">
							<label for="exampleInputEmail1">Name</label> <input type="text"
								class="form-control" id="exampleInputName" name=name
								value="${admin.name}" aria-describedby="nameHelp"
								required="required"> <small id="namelHelp"
								class="form-text text-muted">Enter Full Name </small>
							<form:errors path="adminModel.name" cssClass="error" />
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" value="${admin.email}" aria-describedby="emailHelp"
								required="required"> <small id="emailHelp"
								class="form-text text-muted">We'll never share your
								email with anyone else.</small>
							<form:errors path="adminModel.email" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="exampleInputPhoneNo1">Phone No : </label> <input
								type="text" class="form-control" id="exampleInputPhone1"
								name="phoneNo" value="${admin.phoneNo}"
								aria-describedby="PhoneNoHelp" required="required"> <small
								id="PhoneNoHelp" class="form-text text-muted">We'll
								never share your Phone No with anyone else.</small>
							<form:errors path="adminModel.phoneNo" cssClass="error" />
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								name="password" value="${admin.password}" required="required">
							<form:errors path="adminModel.password" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Confirm Password</label> <input
								type="password" class="form-control" id="exampleInputPassword2"
								name="confirmPassword" value="${admin.confirmPassword}"
								required="required">
							<form:errors path="adminModel.confirmPassword" cssClass="error" />
						</div>
						<div
							style="color: red; text-transform: capitalize; padding-bottom: 1rem">${error }</div>
						<div class="container text-center">
							<a href="/Admin/AdminList/${AdminEmail}"
								class="btn btn-outline-danger">Back</a>
							<button type="submit" class="btn btn-outline-primary">Submit</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous"></script>


</body>
</html>
