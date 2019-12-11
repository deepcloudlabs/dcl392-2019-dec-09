<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<form action="compute" method="post">
	   n: <input type="text" value="${param.n}" 
	   name="n">
	   <button>Compute</button>
	</form>
	<h3>${fibonacci}</h3>
</body>
</html>