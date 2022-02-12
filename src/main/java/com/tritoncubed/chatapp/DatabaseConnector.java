//package com.tritoncubed.chatapp;

/* 
 *
 * Please make sure to set up a credentials file prior to running @ ~/.aws/credentials
 * - See here: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html
 * 
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.policy.Condition;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

// refer to https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html
public class DatabaseConnector {

    private AmazonDynamoDB dynamoDB;
    private Table table;
    private String tableName = "UserLogins";

    public DatabaseConnector() {
        /* 
         * First read credentials
         * Then initialize server
         */

        // Credentials are read from ~/.aws/credentials
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                "Cannot load the credentials from the credential profiles file. " +
                "Please make sure that your credentials file is at the correct " +
                "and is in valid format.",
                e);
        }

        dynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(credentialsProvider)
            .withRegion("US_EAST_2")
            .build();

        // Create the table if it doesn't exist yet, or default to the existing table if it does
        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
            .withKeySchema(new KeySchemaElement().withAttributeName("name").withKeyType(KeyType.HASH))
            .withAttributeDefinitions(new AttributeDefinition().withAttributeName("name").withAttributeType(ScalarAttributeType.S))
            .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
        TableUtils.createTableIfNotExists(dynamoDB, createTableRequest);
        TableUtils.waitUntilActive(dynamoDB, tableName);


    }

    public boolean AddUser(String Username, String pwd, String email) {

        Map < String, AttributeValue > item = newItem(Username, pwd, email);

        try {
            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
        } catch (Exception e) {
            // System.out.println(e);
            return false;
        }
        return true;
    }

	public boolean RemoveUser(String email) {
		// Looks up table key first
		List<Map<String, AttributeValue>> item = Lookup(email);

		try {
			dynamoDB.deleteItem(tableName, item.get(0));
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}


  // Some fixes necessary
  public List<Map<String, AttributeValue>> Lookup(String email) {    
    
 // Scan items for movies with a year attribute greater than 1985
    HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
    Condition condition = new Condition()
        .withComparisonOperator(ComparisonOperator.EQ.toString())
        .withAttributeValueList(new AttributeValue().withS(email));
    scanFilter.put("email", condition);
    ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
    ScanResult scanResult = dynamoDB.scan(scanRequest);
    return scanResult.getItems();
  }

  // Add checking for whether a user with the same name or email already exists

  private Map < String, AttributeValue > newItem(String name, String pwd, String email) {
      String uuid = generateUUID();
      Map < String, AttributeValue > item = new HashMap < String, AttributeValue > ();
      item.put("username", new AttributeValue(name));
      item.put("uuid", new AttributeValue(uuid));
      item.put("pwd", new AttributeValue(pwd));
      item.put("email", new AttributeValue(email));
      return item;
  }

    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}