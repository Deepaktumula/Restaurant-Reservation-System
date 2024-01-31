package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.in.oupp.training.java.advancejava.mvc.models.Login;

public class LoginDao {
	public boolean accountLogin(Login login) throws ClassNotFoundException {
		boolean isValidate = false;
		String SELECT_LOGIN_QUERY = "SELECT email, password FROM login WHERE email = ?";
		String dbEmail = null;
		String dbpassword = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/RestaruantReservationSystem?useSSL=false", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_QUERY);
			preparedStatement.setString(1, login.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				dbEmail = resultSet.getString(1);
				dbpassword = resultSet.getString(2);
			}
			if ((dbEmail.equals(login.getEmail())) && dbpassword.equals(login.getPassword())) {
				isValidate = true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return isValidate;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState : " + ((SQLException) e).getSQLState());
				System.err.println("Error Code : " + ((SQLException) e).getErrorCode());
				System.err.println("Message : " + e.getMessage());
				Throwable throwable = ex.getCause();
				while (throwable != null) {
					System.err.println("Cause : " + throwable);
					throwable = throwable.getCause();
				}
			}
		}

	}
}
