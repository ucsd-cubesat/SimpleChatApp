package com.tritoncubed.chatapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
//import com.google.gson.Gson;

// TODO: follow java naming conventions
public class MessageServlet extends HttpServlet {


    public void init() throws ServletException {

    }

    /*
     * http Get request. messages received from database. Received messages should be written to JSON
     * Called by the servers service method
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long messageId;
        String username;
        String message;
        Date timestamp;
        long userId;

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String messageIdParam = request.getParameter("messageId");
        String usernameParam = request.getParameter("username");
        String messageParam = request.getParameter("message");
        String timestampParam = request.getParameter("timestamp");
        String userIdParam = request.getParameter("userId");

    }

    //Http Post request. Used to post messages from frontend to database
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

}

