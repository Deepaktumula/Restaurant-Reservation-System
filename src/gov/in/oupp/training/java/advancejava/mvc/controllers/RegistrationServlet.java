package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.RegistrationDao;
import gov.in.oupp.training.java.advancejava.mvc.models.Registration;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RegistrationDao registrationDao;

	public void init() {
		registrationDao = new RegistrationDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		Registration registration = new Registration();
		if (firstName.length() != 0) {
			registration.setFirstName(firstName);
			if (lastName.length() != 0) {
				registration.setLastName(lastName);
				// Taking the regular expression
				String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
				// Compiling the regex regular expression
				Pattern pattern = Pattern.compile(regex);
				// creating the instance of matcher
				Matcher matcher = pattern.matcher(email);
				// checking the condition
				if (matcher.matches()) {
					registration.setEmail(email);
					if (phoneNumber.length() == 10) {
						registration.setPhoneNumber(phoneNumber);
						if (password.equals(confirmPassword)) {
							registration.setPassword(password);
							registration.setConfirmPassword(confirmPassword);
						} else {
							System.out.println("Password Missmatch");
						}
					} else {
						System.out.println("PhoneNumber Should be 10 digits");
					}
				} else {
					System.out.println("Email is Invalid");
				}
			} else {
				System.out.println("LastName Cannot be Null");
			}
		} else {
			System.out.println("FirstName Cannot be Null");
		}
		try {
			registrationDao.accountRegistration(registration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("Home.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
