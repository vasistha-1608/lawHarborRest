<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<title>Admin Home</title>
</head>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-link active" aria-current="page"
					href="/Admin/home/${AdminEmail }">Admin Home</a> <a class="nav-link "
					href="/Admin/UserList/${AdminEmail }">User List </a> 
					<a class="nav-link" href="/Admin/LawyerList/${AdminEmail}">Lawyer List</a> 
					<a class="nav-link" href="/Admin/AdminList/${AdminEmail }">Admin List</a> 
					<a class="btn btn-primary" href="/AdminLogout/${AdminEmail }">Logout</a>
			</div>
		</div>
	</div>
</nav>
<div class="container mt-5 text-center">
	<h2>Welcome to Law Harbor Admin Home</h2>
	<a href="/Admin/UserList/${AdminEmail }" class="btn btn-primary">Click to view User List</a>
		<a href="/Admin/LawyerList/${AdminEmail }" class="btn btn-primary">Click to view Lawyer List</a>
		<a href="/Admin/AdminList/${AdminEmail }" class="btn btn-primary">Click to view Admin List</a>
</div>
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