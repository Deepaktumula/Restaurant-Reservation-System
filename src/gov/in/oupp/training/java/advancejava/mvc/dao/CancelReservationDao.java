package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.in.oupp.training.java.advancejava.mvc.models.Cancellation;

public class CancelReservationDao {
	boolean isCancel = false;

	public boolean cancelReservation(Cancellation cancellation) throws ClassNotFoundException {
		String TABLE_ID_QUERY = "SELECT table_id FROM reservation WHERE customerName = ? AND phoneNumber = ? AND partySize = ? AND booking_status = 1";
		String CANCEL_QUERY = "UPDATE reservation SET booking_status = 0 WHERE customerName = ? AND phoneNumber = ? AND partySize = ?";
		String UPDATE_AVAILABLE_QUERY = "UPDATE table_reservation SET available = 1 WHERE id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Cancellation Driver Loaded");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/RestaruantReservationSystem?useSSL=false", "root", "root");
			System.out.println("Cancel DB Successful");
			connection.setAutoCommit(false);

			// Getting table_id from reservation
			PreparedStatement preparedStatement = connection.prepareStatement(TABLE_ID_QUERY);
			preparedStatement.setString(1, cancellation.getCustomerName());
			preparedStatement.setString(2, cancellation.getPhoneNumber());
			preparedStatement.setInt(3, cancellation.getPartySize());
			ResultSet resultSet = preparedStatement.executeQuery();
			int tableID = 0;
			if (resultSet.next()) {
				tableID = resultSet.getInt(1);

				// Canceling the Reservation
				preparedStatement = connection.prepareStatement(CANCEL_QUERY);
				preparedStatement.setString(1, cancellation.getCustomerName());
				preparedStatement.setString(2, cancellation.getPhoneNumber());
				preparedStatement.setInt(3, cancellation.getPartySize());
				preparedStatement.executeUpdate();

				// Updating the available field in table_reservation to 1
				preparedStatement = connection.prepareStatement(UPDATE_AVAILABLE_QUERY);
				preparedStatement.setInt(1, tableID);
				preparedStatement.executeUpdate();
				isCancel = true;
			}
			connection.commit();
		} catch (SQLException e) {
			printSQLException(e);
		}

		return isCancel;
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
