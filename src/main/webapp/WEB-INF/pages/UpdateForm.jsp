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

<title>Update Form</title>
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


					<h1 class="text-center">Update Form</h1>

					<form action="/Admin/updateUser/${AdminEmail}" method="post">

						<div class="form-group">
							<label for="exampleInputEmail1">ID :</label> <input type="number"
								class="form-control" id="exampleInputId" name=id
								value="${user.id }" aria-describedby="idHelp"
								readonly="readonly"> <small id="namelHelp"
								class="form-text text-muted">Non Editable Content </small>
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" value="${user.email }" aria-describedby="emailHelp"
								readonly="readonly"> <small id="emailHelp"
								class="form-text text-muted">Non Editable Content </small>
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								name="password" value="${user.password }" readonly="readonly">
							<small id="emailHelp" class="form-text text-muted">Non
								Editable Content </small>

						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Name</label> <input type="text"
								class="form-control" id="exampleInputName" name=name
								value="${user.name }" aria-describedby="nameHelp"
								required="required"> <small id="namelHelp"
								class="form-text text-muted">Enter Full Name </small>
						</div>

						<div class="form-group">
							<label for="exampleInputPhoneNo1">Phone No : </label> <input
								type="text" class="form-control" id="exampleInputPhone1"
								name="phoneNo" value="${user.phoneNo }" required="required"
								aria-describedby="PhoneNoHelp"> <small id="PhoneNoHelp"
								class="form-text text-muted">We'll never share your
								Phone No with anyone else.</small>
							<form:errors path="user.phoneNo" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Confirm Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								name="confirmPassword" required="required"> <small
								id="emailHelp" class="form-text text-muted">please Enter
								the password </small>
						</div>

						<div
							style="color: red; text-transform: capitalize; padding-bottom: 1rem">${error }</div>

						<div class="container text-center">
							<a href="/Admin/UserList/${AdminEmail }"
								class="btn btn-outline-danger">Back</a>
							<button type="submit" class="btn btn-primary">Submit</button>
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
