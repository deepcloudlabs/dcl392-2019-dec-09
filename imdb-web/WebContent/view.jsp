<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p />
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h4 class="card-title">Search Form</h4>
			</div>
			<div class="card-body">
				<form action="list" method="post">
					<util:select label="Genre" name="genre" options="${genres}" property="name"/>
					<button class="btn btn-success">Search</button>
				</form>
			</div>
		</div>
		<c:if test="${not empty movies}">
			<p />
			<div class="card">
				<div class="card-header">
					<h4 class="card-title">Search Result</h4>
				</div>
				<div class="card-body">
					<table class="table table-striped table-hover table-responsive">
						<thead>
							<tr>
								<th>Title</th>
								<th>Name</th>
								<th>Imdb</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${movies}" var="movie">
								<tr>
									<td>${movie.title}</td>
									<td>${movie.year}</td>
									<td><a href="http://www.imdb.com/title/${movie.imdb}"
										target="_blank">Visit me!</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>