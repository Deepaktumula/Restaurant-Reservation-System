<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
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

        .login-card {
            width: 25%;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
        }

        form {
            width: 100%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table td {
            padding: 8px;
            text-align: left;
        }

        input[type="email"],
        input[type="password"] {
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Account Login</h1>
        <% if (request.getAttribute("loginError") != null) { %>
            <p class="error-message"><%= request.getAttribute("loginError") %></p>
        <% } %>
        <div class="login-card">
            <form action="<%=request.getContextPath()%>/login" method="post">
                <table>
                    <tr>
                        <td>Email :</td>
                        <td><input type="email" name="email" placeholder="enter emailId" required></td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td><input type="password" name="password" placeholder="enter password" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Login"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
