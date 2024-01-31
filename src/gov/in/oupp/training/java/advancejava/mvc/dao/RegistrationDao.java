package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gov.in.oupp.training.java.advancejava.mvc.models.Registration;

public class RegistrationDao {
	public int accountRegistration(Registration registration) throws ClassNotFoundException {
		String INSERT_INTO_QUERY = "INSERT INTO registration(" + "firstName," + "lastName," + "email," + "phoneNumber,"
				+ "password," + "confirmPassword)" + "VALUES(?,?,?,?,?,?);";
		int records = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/RestaruantReservationSystem?useSSL=false", "root", "root");
			// Setting AutoCommit - false
			connection.setAutoCommit(false);
			// FOR REGISTRATION TABLE
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_QUERY);
			preparedStatement.setString(1, registration.getFirstName());
			preparedStatement.setString(2, registration.getLastName());
			preparedStatement.setString(3, registration.getEmail());
			preparedStatement.setString(4, registration.getPhoneNumber());
			preparedStatement.setString(5, registration.getPassword());
			preparedStatement.setString(6, registration.getConfirmPassword());

			records = preparedStatement.executeUpdate();
			System.out.println("Inserted " + records + " row(s) into registration table");
			// FOR LOGIN TABLE
			String INSERT_INTO_LOGIN = "INSERT INTO login(login_id, email, password) "
					+ "VALUES((SELECT id FROM registration WHERE email = ? ), ?, ?);";
			preparedStatement = connection.prepareStatement(INSERT_INTO_LOGIN);
			preparedStatement.setString(1, registration.getEmail());
			preparedStatement.setString(2, registration.getEmail());
			preparedStatement.setString(3, registration.getPassword());
			int recordsLogin = preparedStatement.executeUpdate();
			System.out.println("Inserted " + recordsLogin + " row(s) into login table");
			// Committing
			connection.commit();

		} catch (SQLException e) {
			printSQLException(e);
		}

		return records;
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
