package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gov.in.oupp.training.java.advancejava.mvc.models.UpdateReservation;

public class UpdateReservationDao {
	public boolean updateTablereservation(UpdateReservation updateReservation) throws ClassNotFoundException {
		boolean isUpdate = false;

		String UPDATE_SQL_QUERY = "UPDATE reservation SET date = ?, time = ? "
				+ "WHERE customerName = ? AND phoneNumber = ? AND partySize = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Update Reservation Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/RestaruantReservationSystem?useSSL=false", "root", "root");
			System.out.println("Update Reservation DB connection Successful");

			// Update Reservation
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL_QUERY);
			preparedStatement.setString(1, updateReservation.getDate());
			preparedStatement.setString(2, updateReservation.getTime());
			preparedStatement.setString(3, updateReservation.getCustomerName());
			preparedStatement.setString(4, updateReservation.getPhoneNumber());
			preparedStatement.setInt(5, updateReservation.getPartySize());
			// preparedStatement.executeUpdate();
			int records = preparedStatement.executeUpdate();
			if (records == 1) {
				isUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println(updateReservation.getDate());
			printSQLException(e);
		}

		return isUpdate;
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
