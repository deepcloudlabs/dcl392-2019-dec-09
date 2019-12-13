<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Search Page</title>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/knockout-3.3.0.js"></script>
<script type="text/javascript" src="js/imdb.js"></script>
<script type="text/javascript">
         var viewModel= new SearchViewModel();
    $('document').ready(function(){
         ko.applyBindings(viewModel);
         viewModel.init();
    });	
</script>
</head>
<body>
	<table>
	    <tr>
	        <td>From year:</td>
	        <td><input type="text" data-bind="value: fromYear"/></td>
	    </tr>
	    <tr>
	        <td>To year:</td>
	        <td><input type="text" data-bind="value: toYear"/></td>
	    </tr>
	    <tr>
	        <td>Genre:</td>
	        <td><select data-bind="options: genres, value: genre"></select>
	        <input type="button" 
	               value="Search" 
	               data-bind="click: search" /></td>
	    </tr>
	</table>
	<table border="1">
	   <thead>
	      <tr>
	         <th>Title</th>
	         <th>Year</th>
	         <th>Imdb</th>
	      </tr>
	   </thead>
	   <tbody data-bind="foreach: movies">
	   		<tr>
	   			<td data-bind="text: title"></td>
	   			<td data-bind="text: year"></td>
	   			<td><a data-bind="attr: { href: 'http://www.imdb.com/title/'.concat(imdb) }" target="_blank">Visit</a></td>
	   		</tr>
	   </tbody>	
	</table> 
</body>
</html>