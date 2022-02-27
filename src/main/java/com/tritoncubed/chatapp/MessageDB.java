package com.tritoncubed.chatapp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

public class MessageDB {

    /**
     * Retrieves the most recent messages
     * @return
     */
    public static MessageDB getRecentMessage(int messageAmount) {
        //DynamoDBMapper mapper = new DynamoDBMapper(DB);

        Map<String, AttributeValue> hash = new HashMap<String, AttributeValue>();

        QueryRequest request = QueryRequest.builder().build();
    }

}
