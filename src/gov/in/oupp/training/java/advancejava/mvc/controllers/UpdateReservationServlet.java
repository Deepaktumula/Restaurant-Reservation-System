package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.UpdateReservationDao;
import gov.in.oupp.training.java.advancejava.mvc.models.UpdateReservation;

@WebServlet("/updateReservation")
public class UpdateReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UpdateReservationDao updateReservationDao;

	public void init() {
		updateReservationDao = new UpdateReservationDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerName = request.getParameter("customerName");
		String phoneNumber = request.getParameter("phoneNumber");
		int partySize = Integer.parseInt(request.getParameter("partySize"));
		String date = request.getParameter("date");
		String time = request.getParameter("time");

		UpdateReservation updateReservation = new UpdateReservation();
		updateReservation.setCustomerName(customerName);
		updateReservation.setPhoneNumber(phoneNumber);
		updateReservation.setPartySize(partySize);
		updateReservation.setDate(date);
		updateReservation.setTime(time);

		try {
			if (updateReservationDao.updateTablereservation(updateReservation)) {
				System.out.println("Updation Successfully");
				response.sendRedirect("UpdateSuccessful.jsp");
			} else {
				request.setAttribute("updationError", "Something went wrong!.Please try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateReservation.jsp");
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
