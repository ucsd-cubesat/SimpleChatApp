<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> --%>


<!-- <title>Log in</title> -->
<!-- <meta name="viewport" content="width=device-width,initial-scale=1"> -->

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://unpkg.com/tailwindcss@%5E2/dist/tailwind.min.css"
	rel="stylesheet">
</head>

<body>


	<!-- Display the error message pop-up if there was a failed authentication -->
	
	<% if(null != request.getAttribute("errorMessage")) { %>
	   	<div class="pl-5">
			<div class=" absolute inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl border border-red-500 transform transition-all sm:my-8 sm:align-left sm:max-w-lg sm:w-full">
				<div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
					<div class="sm:flex sm:items-start">
						<div class="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10">
							<!-- Heroicon name: outline/exclamation -->
							<svg class="h-6 w-6 text-red-600"
								xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
								stroke="currentColor" aria-hidden="true">
		            			<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
		          			</svg>
						</div>
						<div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
							<h3 class="text-lg leading-6 font-medium text-gray-900"
								id="modal-title"><%= request.getAttribute("errorMessage") %></h3>
							<div class="mt-2">
								<p class="text-sm text-gray-500">Please try signing in again or create a new account.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<%}%>

	<div class="flex h-screen justify-center items-center">
		<div class="w-full max-w-xs">
			<form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
				action="Login">
				<div
					class="text-center text-gray-700 text-lg font-bold mb-2 cursor-text">
					<h1>Login</h1>
				</div>

				<div class="mb-4">
					<label
						class="block text-gray-700 text-sm font-bold mb-2 cursor-text"
						for="username"> Username </label> <input
						class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
						name="username" type="text" placeholder="Username" required>
				</div>

				<div class="mb-6">
					<label class="block text-gray-700 text-sm font-bold mb-2"
						for="password"> Password </label> <input
						class="shadow appearance-none border border-blue-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
						name="password" type="password" placeholder="******************"
						required>
				</div>

				<div class="flex items-center justify-between">
					<input class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit" value="Sign In"> 
					<label class="flex items-center"> 
						<input type="checkbox" class="form-checkbox cursor-pointer" name="isNewAccount" value="1">
						<span class="ml-2">New Account</span>
					</label>
				</div>

				<div
					class="text-center pt-6 font-bold text-sm text-blue-500 hover:text-blue-800 cursor-pointer">
					<a href="#">Forgot Password?</a>
				</div>

			</form>
			<p class="text-center text-gray-500 text-xs">&copy;Placeholder.
				All rights reserved.</p>
		</div>
	</div>

</body>
</html>

