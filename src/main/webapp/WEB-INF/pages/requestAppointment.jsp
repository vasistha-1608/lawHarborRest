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

<title>Request Appointment</title>
</head>
<body>

	<div class="container mt-5">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-body">


					<h1 class="text-center">Request Appointment with lawyer</h1>

					<form action="/User/booking/${lawyer.email}/${user.email}" method="post">


						<div class="form-group">
							<label for="exampleInputEmail1">Name</label> <input type="text"
								class="form-control" id="exampleInputName" name=name
								value="${lawyer.name }" aria-describedby="nameHelp" readonly="readonly">
							<small id="namelHelp" class="form-text text-muted"
								></small>
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Email address of lawyer</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" value="${lawyer.email }"
								aria-describedby="emailHelp" readonly="readonly"> <small id="emailHelp"
								class="form-text text-muted">We'll never share your
								email with anyone else.</small>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Your email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								name="email" value="${user.email}" aria-describedby="emailHelp" readonly="readonly" > 
							<small id="emailHelp" class="form-text text-muted">We'll
								never share your email with anyone else.</small>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Law firm Name</label> <input
								type="text" class="form-control" id="exampleInputName"
								name="lawfirmName" aria-describedby="nameHelp" required="required" > <small
								id="namelHelp" class="form-text text-muted">Enter Full
								Name </small>
						</div>
						<div class="form-group">
							<label>Enter appointment date</label> <input type="date"
								name="date" class="form-control" required="required" >
						</div>
						<div class="form-group">
							<label>Enter appointment time</label> <input type="time"
								name="time" class="form-control" required="required" >
						</div>

						<div class="container text-center">
							<a href="/Lawyer/getLawyers/${user.email }" class="btn btn-outline-danger">Back</a>
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