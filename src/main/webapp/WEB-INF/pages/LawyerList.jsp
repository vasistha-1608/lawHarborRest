<%@page import="java.util.List"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
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

<title>Lawyer List</title>
</head>
<body>
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
				<a class="nav-link " href="/Admin/home/${AdminEmail }">Admin Home</a> 
				<a class="nav-link " href="/Admin/UserList/${AdminEmail }">User List </a> 
				<a class="nav-link active" aria-current="page" href="/Admin/LawyerList/${AdminEmail}">Lawyer List</a> 
				<a class="nav-link" href="/Admin/AdminList/${AdminEmail } ">Admin List</a> 
				<a class="btn btn-primary" href="/AdminLogout/${AdminEmail }">Logout</a>
			</div>
		</div>
	</div>
</nav>
	<div class="container mt-5">
		<h1 class="text-center">List Of Lawyers </h1>
		
		<a href="/Admin/LawyerRegPage/${AdminEmail }" class="btn btn-primary text-center mb-3">Add
			Lawyer</a>
<br>
		<table class="table">
		<caption>Lawyer List</caption>
			<thead class="thead-dark">
				<tr>
					<th scope="col"># ID</th>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Phone No</th>
					<th scope="col">Domain</th>
					<th scope="col">Experience</th>
					<th scope="col" colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lawyer" items="${LawyerList }">
					<tr>
						<th scope="row">${lawyer.id }</th>
						<td>${lawyer.name }</td>
						<td>${lawyer.email }</td>
						<td>${lawyer.phoneNo }</td>
						<td>${lawyer.domain }</td>
						<td>${lawyer.experience }</td>
						<td><a href="/Admin/deleteLawyer/${AdminEmail }/${lawyer.id}"><i
								class="fas fa-trash text-danger"></i></a></td>
						<td><a href="/Admin/updateLawyer/${AdminEmail }/${lawyer.id}"><i class="fa fa-pen"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/9e779de986.js"
		crossorigin="anonymous"></script>





</body>
</html>