package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.CancelReservationDao;
import gov.in.oupp.training.java.advancejava.mvc.models.Cancellation;

@WebServlet("/cancelReservation")
public class CancelReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CancelReservationDao cancelReservationDao;

	// initialization cancelReservationDao
	public void init() {
		cancelReservationDao = new CancelReservationDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerName = request.getParameter("customerName");
		String phoneNumber = request.getParameter("phoneNumber");
		int partySize = Integer.parseInt(request.getParameter("partySize"));
		if (partySize > 0 && partySize < 3) {
			partySize = 2;
		}
		if (partySize > 2 && partySize < 5) {
			partySize = 4;
		}
		if (partySize > 4 && partySize < 9) {
			partySize = 8;
		}

		Cancellation cancellation = new Cancellation();
		cancellation.setCustomerName(customerName);
		cancellation.setPhoneNumber(phoneNumber);
		cancellation.setPartySize(partySize);

		try {
			if (cancelReservationDao.cancelReservation(cancellation)) {
				System.out.println("Cancelled Successfully");
				response.sendRedirect("CancelSuccessful.jsp");
			} else {
				request.setAttribute("cancellationError", "No bookings from you " + customerName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Reservation.jsp");
				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}
