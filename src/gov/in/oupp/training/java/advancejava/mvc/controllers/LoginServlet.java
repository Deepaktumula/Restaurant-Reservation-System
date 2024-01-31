package gov.in.oupp.training.java.advancejava.mvc.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.in.oupp.training.java.advancejava.mvc.dao.LoginDao;
import gov.in.oupp.training.java.advancejava.mvc.models.Login;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);

		try {
			if (loginDao.accountLogin(login)) {
				response.sendRedirect("Restaurant.jsp");
			} else {
				request.setAttribute("loginError", "Invalid credentials. Please try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
			System.out.println("-------------------------------");
			System.out.println("User Email :: " + email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
}
