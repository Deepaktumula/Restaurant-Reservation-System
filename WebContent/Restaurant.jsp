<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant</title>
<style>
	body {
		font-family: Arial, sans-serif;
		margin: 0;
		padding: 0;
		background-color: lightblue;
	}
	
	h1 {
		text-align: center;
		margin-top:8rem;
		margin-bottom: -8rem;
	}

	table {
		width: 100%;
		height: 100vh;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	td {
		padding: 20px;
		text-align: center;
	}
	
	button {
		padding: 10px 20px;
		font-size: 16px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		background-color: #4CAF50;
		color: white;
		transition-duration: 0.4s;
	}
	
	button:hover {
		background-color: #45a049;
	}
</style>
</head>
<body>
	<h1>Welcome to Our Restaurant</h1>
	<table>
		<tr>
			<td><a href="Reservation.jsp"><button>Book Table</button></a></td>
			<td><a href="Cancel.jsp"><button>Cancel Table</button></a></td>
			<td><a href="UpdateReservation.jsp"><button>Update Table</button></a></td>
		</tr>
	</table>
</body>
</html>
