<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lottery Page</title>
</head>
<body>
	<form method="post">
	<input type="submit" value="Draw" />
	</form>
	<ul>
		<c:forEach items="${lottery.numbers}" var="num">
			<li>${num}</li>
		</c:forEach>
	</ul>
</body>
</html>