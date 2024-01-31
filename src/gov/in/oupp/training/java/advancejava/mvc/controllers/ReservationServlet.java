package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.ReservationDao;
import gov.in.oupp.training.java.advancejava.mvc.models.Reservation;

@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ReservationDao reservationDao;

	public void init() {
		reservationDao = new ReservationDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerName = request.getParameter("customerName");
		String phoneNumber = request.getParameter("phoneNumber");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
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
		Reservation reservation = new Reservation();
		reservation.setCustomerName(customerName);
		reservation.setPhoneNumber(phoneNumber);
		reservation.setDate(date);
		reservation.setTime(time);
		reservation.setPartySize(partySize);

		try {
			if (reservationDao.tableReservation(reservation)) {
				response.sendRedirect("confirmPage.jsp");
			} else {
				request.setAttribute("reservationError",
						"You can choose tables with other capacity size. Please try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Reservation.jsp");
				dispatcher.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
