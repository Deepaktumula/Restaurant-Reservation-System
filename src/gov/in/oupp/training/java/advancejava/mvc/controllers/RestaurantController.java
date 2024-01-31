package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.RestaurantDao;
import gov.in.oupp.training.java.advancejava.mvc.dao.RestaurantDaoImpl;
import gov.in.oupp.training.java.advancejava.mvc.models.Restaurant;

@WebServlet("/restaurant")
public class RestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RestaurantDao restaurantDao = new RestaurantDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Restaurant> restaurants = restaurantDao.getAllRestaurants();
		request.setAttribute("restaurants", restaurants);
		request.getRequestDispatcher("Restaurant.jsp").forward(request, response);
	}

	// Other methods for handling POST requests to add/update restaurants

	// For example, adding a new restaurant
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		int capacity = Integer.parseInt(request.getParameter("capacity"));

		Restaurant newRestaurant = new Restaurant();
		newRestaurant.setName(name);
		newRestaurant.setAddress(address);
		newRestaurant.setPhoneNumber(phoneNumber);
		newRestaurant.setCapacity(capacity);
		
		try {
			restaurantDao.addRestaurant(newRestaurant);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("Home.jsp");
	}
}
