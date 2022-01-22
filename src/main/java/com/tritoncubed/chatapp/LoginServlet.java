package com.tritoncubed.chatapp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicReference;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tritoncubed.launchapi.exceptions.APIException;

/**
 * A servlet that performs a small ping for the user. A simple GET request is made, and a simple
 * acknowledgement is sent back.
 * 
 * http://launchapi.tritoncubed.com/ping
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 6659111915305916881L;

    public LoginServlet() {
        super();
    }

    /**
     * This method contains the logic of the ping request. The request itself is contained in its
     * namesake object, and the response is created in its namesake object.
     * 
     * The response is a JSON object with the following layout:
     * {
     *     "response": "pong",
     *     "timestamp": 0123456789
     * }
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String username = request.getParameter("username");
    	  String password = request.getParameter("password");
    	  
    	  AmazonDynamoDB DB = AmazonDynamoDBClient.builder()
                  .withRegion(Regions.US_EAST_2)
                  .build();

           DynamoDB dynamoDB = new DynamoDB(DB);
           Table table = dynamoDB.getTable("UserLogins");
           
           final Map<String, Object> infoMap = new HashMap<String, Object>();
           
           infoMap.put("password", password);

           try {
               System.out.println("Adding a new item...");
               PutItemOutcome outcome = table
            		   .putItem(new Item().withPrimaryKey("username", username).withMap("info", infoMap));

               System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

           }
           catch (Exception e) {
               System.err.println("Unable to add item: ");
               System.err.println(e.getMessage());
           }
           
    	  response.getWriter().append("Login Attempt Complete");
    }
}
