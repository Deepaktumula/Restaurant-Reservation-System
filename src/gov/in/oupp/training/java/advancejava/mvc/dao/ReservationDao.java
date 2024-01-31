package gov.in.oupp.training.java.advancejava.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.in.oupp.training.java.advancejava.mvc.models.Reservation;

public class ReservationDao {
	public boolean tableReservation(Reservation reservation) throws ClassNotFoundException {
		boolean isAvailable = true;

		String INSERT_INTO_SQL = "INSERT INTO reservation(customerName, phoneNumber, partySize, date, time, id, table_id, booking_status) "
				+ "VALUES (?,?,?,?,?, " + "(SELECT id FROM registration WHERE phoneNumber = ? ), " + "?, ?)";

		int insert_records = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Reservation Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/RestaruantReservationSystem?useSSL=false", "root", "root");
			System.out.println("Reservation DB Connection Success");
			connection.setAutoCommit(false);

			// Selecting Only one ID from table_reservation
			String ID_Query = "SELECT id FROM table_reservation WHERE capacity = ? AND available = 1 LIMIT 1";
			PreparedStatement preparedStatement = connection.prepareStatement(ID_Query);
			preparedStatement = connection.prepareStatement(ID_Query);
			preparedStatement.setInt(1, reservation.getPartySize());
			ResultSet resultSet = preparedStatement.executeQuery();
			int tableID = 0;
			if (resultSet.next()) {
				tableID = resultSet.getInt("id");
				// Inserting data in reservation table
				preparedStatement = connection.prepareStatement(INSERT_INTO_SQL);
				preparedStatement.setString(1, reservation.getCustomerName());
				preparedStatement.setString(2, reservation.getPhoneNumber());
				preparedStatement.setInt(3, reservation.getPartySize());
				preparedStatement.setString(4, reservation.getDate());
				preparedStatement.setString(5, reservation.getTime());
				preparedStatement.setString(6, reservation.getPhoneNumber());
				preparedStatement.setInt(7, tableID);
				preparedStatement.setInt(8, 1);

				insert_records = preparedStatement.executeUpdate();
				if (insert_records < 1) {
					isAvailable = false;
				}
				System.out.println("Inserted " + insert_records + " in reservation table");

				// After reservation updating the value of capacity
				// table_reservation
				String UPDATE_QUERY = "UPDATE table_reservation SET available = ? WHERE id = ? ";
				preparedStatement = connection.prepareStatement(UPDATE_QUERY);
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, tableID);
				int updated_records = preparedStatement.executeUpdate();
				System.out.println("Update " + updated_records + " in table_reservation table");
			} else {
				System.out.println("No available tables with the specified capacity.");
				isAvailable = false;
			}

			connection.commit();
		} catch (SQLException e) {
			printSQLException(e);
		}

		return isAvailable;
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
