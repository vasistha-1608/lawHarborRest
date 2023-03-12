<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>Login Form</title>
</head>
<body>

	<div class="container mt-5">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-body">

					<h1 class="text-center">Login Form</h1>

					<form action="/getLogin" method="post">

						<div class="form-group">
							<label for="exampleFormControlSelect1">select User Type</label> <select
								class="form-control" id="exampleFormControlSelect1" name="role" >
								<option value="User" selected="selected">User</option>
								<option value="Lawyer">Lawyer</option>
								<option value="Admin">Admin</option>

							</select>
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Email Id</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" value="${email }" aria-describedby="emailHelp"
								required="required"> <small id="emailHelp"
								class="form-text text-muted">We'll never share your
								email with anyone else.</small>
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								name="password" value="${ password}" required="required">
						</div>
						<div
							style="color: red; text-transform: capitalize; padding-bottom: 0.8rem ">${error }</div>
						<div
							style="color:blue; text-transform: capitalize; padding-bottom: 0.8rem ">${msg }</div>
						<div class="container text-center">
							<a href="/registerForm" class="btn btn-outline-danger">Register</a>
							<button type="submit" class="btn btn-outline-primary">Login</button>
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
