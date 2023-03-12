<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.ui.Model"%>
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

<title>Booking List</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="/User/home/${UserEmail }">Home</a> <a
						class="nav-link " href="/User/getLawyers/${UserEmail }">Lawyer
						List </a> <a class="nav-link active" aria-current="page"
						href="/User/Booking/${UserEmail}">Booking List </a> <a
						class="nav-link " href="/CaseRecord/usercaserecord/${UserEmail}">Case
						Records </a> <a class="btn btn-primary"
						href="/UserLogout/${UserEmail}">Logout</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-5">


		<table class="table">
			<caption class="text-center">List Of Bookings</caption>
			<thead class="thead-dark">
				<tr>
					<th scope="col"># ID</th>
					<th scope="col">Client Name</th>
					<th scope="col">Lawyer Name</th>
					<th scope="col">Law Farm</th>
					<th scope="col">Date</th>
					<th scope="col">Time</th>
					<th scope="col">Booking Status</th>
					<th scope="col" colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${BookingList }">
					<tr>
						<th scope="row">${booking.bookingId }</th>
						<td>${booking.clientDetail.name }</td>
						<td>${booking.lawyerDetail.name }</td>
						<td>${booking.lawfirmName }</td>
						<td>${booking.date }</td>
						<td>${booking.time }</td>
						<td><c:if test="${booking.bookingStatus==NULL}">Request pending</c:if>
							<c:if test="${booking.bookingStatus== true }">Request accepted</c:if>
							<c:if test="${booking.bookingStatus== false }">Request rejected</c:if>
						</td>
						<td><c:if test="${booking.bookingStatus== true }">
								<a href="/CaseRecord/usercaserecord/${UserEmail}"
									class="btn btn-outline-primary">View</a>
								<a href="/User/deletebooking/${UserEmail }/${booking.bookingId}"
									class="btn btn-outline-danger">Delete</a>
							</c:if> <c:if test="${booking.bookingStatus== false }">
								<a href="/User/deletebooking/${UserEmail }/${booking.bookingId}"
									class="btn btn-outline-danger">Delete</a>
							</c:if></td>
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