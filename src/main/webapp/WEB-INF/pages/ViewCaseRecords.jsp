<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.ui.Model"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>Case Record</title>
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
					<a class="nav-link " aria-current="page" href="/Lawyer/home/${UserId }">Home</a> 
					<a class="nav-link" href="/Lawyer/Booking/${UserId}">Booking List </a> 
					<a class="nav-link active" href="/CaseRecord/viewCaseRecords/${UserId}">Case Record</a>
					<a class="btn btn-primary" href="/LawyerLogout/${UserId}" >Logout</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container mt-5">
		<h1 class="text-center">List Of Case Records .</h1>

		<table class="table">
		<caption>accepted booking records</caption>
			<thead class="thead-dark">
				<tr>
					<th scope="col"># ID</th>
					<th scope="col">Name</th>
					<th scope="col">Booking Id</th>
					<th scope="col">Date</th>
					<th scope="col">Event Details</th>
					<th scope="col">Action Taken</th>
					<th scope="col">Issued By</th>
					<th scope="col" colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${RecordList }">
					<tr>
						<th scope="row">${record.caseRecordId }</th>
						<td>${record.user.name }</td>
						<td>${record.bookingDetails.bookingId }</td>
						<td>${record.date }</td>
						<td>${record.eventDetails }</td>
						<td>${record.actionTaken }</td>
						<td>${record.issuedBy.name }</td>
						<td><a href="/CaseRecord/deleteRecord/${record.caseRecordId}"><i
								class="fas fa-trash text-danger"></i></a></td>
						<td><a
							href="/CaseRecord/displayUpdateCaseRecord/${record.caseRecordId}"><i
								class="fa fa-pen"></i></a></td>
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