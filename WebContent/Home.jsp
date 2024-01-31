<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page
	import="gov.in.oupp.training.java.advancejava.mvc.models.Restaurant"%>

<!DOCTYPE html>
<html>
<head>
<title>Restaurant List</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

h1 {
	color: #333;
}

ul {
	list-style-type: none;
	padding: 0;
}

li {
	border: 1px solid #ccc;
	margin-bottom: 10px;
	padding: 10px;
}

a {
	text-decoration: none;
	color: #0066cc;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>Restaurant List</h1>
	<ul>
		<%
			Object restaurantsObj = request.getAttribute("restaurants");
			if (restaurantsObj != null && restaurantsObj instanceof List) {
				List<Restaurant> restaurants = (List<Restaurant>) restaurantsObj;
				for (Restaurant restaurant : restaurants) {
		%>
		<li><strong>Name:</strong> <%=restaurant.getName()%><br> <strong>Address:</strong>
			<%=restaurant.getAddress()%><br> <strong>Phone
				Number:</strong> <%=restaurant.getPhoneNumber()%><br> <strong>Capacity:</strong>
			<%=restaurant.getCapacity()%><br></li>
		<%
			} // End of for loop
			} else {
		%>
		<li>No restaurants found</li>
		<%
			} // End of if-else block
		%>
	</ul>
	<a href="Registration.jsp"><button>Registration</button></a>
	<a href="Login.jsp"><button>Login</button></a>
</body>
</html>
