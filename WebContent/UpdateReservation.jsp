<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UpdationPage</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: lightblue;
        }

        /* Form container */
        form {
            width: 30%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Form elements */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 8px;
        }

        input[type="text"],
        input[type="tel"],
        input[type="time"],
        input[type="date"] {
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
            text-align: center;
        }
    </style>
</head>
<body>
	<h1 style="text-align:center;margin-top:7rem">Updation Form</h1>
    <%
        if (request.getAttribute("updationError") != null) {
    %>
    <p class="error-message"><%=request.getAttribute("updationError")%></p>
    <%
        }
    %>
    <form action="<%=request.getContextPath()%>/updateReservation" method="post">
        <table>
            <tr>
                <td>FullName :</td>
                <td><input type="text" name="customerName" placeholder="Enter full name" required></td>
            </tr>
            <tr>
                <td>PhoneNumber :</td>
                <td><input type="tel" name="phoneNumber" placeholder="Enter phone number" required></td>
            </tr>
            <tr>
                <td>PartySize :</td>
                <td><input type="number" name="partySize" placeholder="Enter party size" required></td>
            </tr>
            <tr>
                <td>Update Date :</td>
                <td><input type="date" name="date" placeholder="Enter updation date" required></td>
            </tr>
            <tr>
                <td>Update Time :</td>
                <td><input type="time" name="time" placeholder="Enter updation time" required></td>
            </tr>
            <tr>
                <td colspan="2" class="update"><input type="submit" value="Update Reservation"></td>
            </tr>
        </table>
    </form>
</body>
</html>
