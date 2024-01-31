<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Form</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: lightblue;
}

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

.reservation-card {
	width: 25%;
	margin-top: 2rem;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	box-sizing: border-box;
}

h1 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table td {
	padding: 8px;
	text-align: left;
}

input[type="text"], input[type="time"],, input[type="date"], input[type="tel"], input[type="number"]
	{
	width: calc(100% - 8px);
	padding: 8px;
	margin-bottom: 10px;
	border-radius: 4px;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

input[type="submit"] {
	width: 100%;
	padding: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	background-color: #4CAF50;
	color: white;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	transition-duration: 0.4s;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

p.error-message {
	color: red;
	margin-top: 1rem;
}

div.cancel {
	margin-top: 10px;
	border-radius: 4px;
	text-align: center;
	color: white;
	background-color: #ff0000;
	padding: 10px;
}

div.cancel a {
	text-decoration: none;
	color: white;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Table Reservation</h1>
		<div class="reservation-card">
			<%
                if (request.getAttribute("reservationError") != null) {
            %>
			<p class="error-message"><%=request.getAttribute("reservationError")%></p>
			<%
                }
            %>
			<form action="<%=request.getContextPath()%>/reserve" method="post">
				<table>
					<tr>
						<td>FullName :</td>
						<td><input type="text" name="customerName"
							placeholder="enter fullname" required></td>
					</tr>
					<tr>
						<td>PhoneNumber :</td>
						<td><input type="tel" name="phoneNumber"
							placeholder="enter phonenumber" required></td>
					</tr>
					<tr>
						<td>PartySize :</td>
						<td><input type="number" name="partySize"
							placeholder="enter partysize" required></td>
					</tr>
					<tr>
						<td>Date :</td>
						<td><input type="date" name="date" required></td>
					</tr>
					<tr>
						<td>Time :</td>
						<td><input type="time" name="time" placeholder="enter time"
							required></td>
					</tr>
					<tr>
						<td colspan="2" class="reserve"><input type="submit"
							value="Reserve"></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>
</html>
