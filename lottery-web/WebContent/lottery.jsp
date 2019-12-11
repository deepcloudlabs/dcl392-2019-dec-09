<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Lottery Page</title>
</head>
<body>
	<form action="draw" method="post">
		<label for="n">n:</label> <input type="text" id="n" name="n"></input>
		<button>Draw</button>
	</form>
	<c:if test="${not empty lottery.numbers}">
		<table>
			<thead>
				<tr>
					<c:forEach begin="1" end="6" step="1" var="i">
							<th>Column #${i}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lottery.numbers}" var="numbers">
					<tr>
						<c:forEach items="${numbers}" var="number">
							<td>${number}</td>	
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>