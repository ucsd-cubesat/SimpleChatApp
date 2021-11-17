package com.tritoncubed.chatapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 * 
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean auth(String userId, String password) {
		/*
		 * Edit this method to check for correct Usernames and Passwords in DynamoDB
		 */
		return true;
	}

	private boolean createAccount(String userId, String password) {
		/*
		 * Edit this method to add a new account with given username and password in
		 * DynamoDB
		 */
		return true; // True if successful
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		String username = request.getParameter("username");
		String password = request.getParameter("userPassword");
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());

		// Check to see if it is a new account
		boolean newUser = false;
		for (String item : parameterNames) {
			if (item.equals("isNewAccount"))
				newUser = true;
		}
		System.out.println(Collections.list(request.getParameterNames()));

		// Record user credentials for this session
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		// Check for login status!

		// If new user, record a new user
		if (newUser) {
			System.out.println("You are a new user!");

			// placeholder method - does nothing
			createAccount(username, password);

			// For now, redirect to the chat page
			request.getRequestDispatcher("/index.html").forward(request, response);
		}

		// If they aren't a new user, then try to authenticate
		// Placeholder method to check whether the person is authenticated (for now,
		// this is always true)
		else if (auth(username, password)) {

			System.out.println("Welcome back!");

			request.getRequestDispatcher("/index.html").forward(request, response);
		}

		// Else, you're a stranger! Redirect to login page.
		else {
			
//			session.invalidate();
//			request.setAttribute("errorMessage", "Account Authentication Failed");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		assert true;
	}

}