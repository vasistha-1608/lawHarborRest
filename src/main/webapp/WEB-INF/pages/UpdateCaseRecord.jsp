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

<title>Update Case Record </title>
</head>
<body>

	<div class="container mt-5">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-body">

					<h1 class="text-center">Update Case Record Form</h1>

					<form action="/CaseRecord/updateCaseRecord/${CaseRecord.issuedBy.id }" method="post">

						<div class="form-group">
							<label for="exampleInputEmail1">CaseRecord ID :</label> <input
								type="text" class="form-control" id="exampleInputId"
								name=caseRecordId value="${CaseRecord.caseRecordId }"
								aria-describedby="idHelp">
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Date :</label> <input type="date"
								class="form-control" id="exampleInputDate1" name="date" value ="${CaseRecord.date}"
								aria-describedby="dateHelp">
						</div>
						<div class="form-group">
							<label for="exampleFormControlTextarea1">Event Details : </label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
							name="eventDetails"
								rows="3">${CaseRecord.eventDetails }</textarea>
						</div>

						<div class="form-group">
							<label for="exampleFormControlTextarea1">Action Taken : </label>
							<textarea class="form-control" id="exampleFormControlTextarea2"
							name = "actionTaken"
								rows="6">${CaseRecord.actionTaken }</textarea>
						</div>


						<div class="container text-center">
							<a href="/CaseRecord/viewCaseRecords/${CaseRecord.issuedBy.id}" class="btn btn-outline-danger">Back</a>
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>
>
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
