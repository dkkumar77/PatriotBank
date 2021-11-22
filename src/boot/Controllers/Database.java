package boot.Controllers;

/**
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;

/**
 * Holds methods related to database management.
 * */
public class Database {

    /*changed from no access modifier to public access modifier*/
    public Table table;

    /**
     * Class constructor. Sets the AWS Region and Endpoint.
     * Initializes table.
     * */
    public Database() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);
        this.table = dynamoDB.getTable("data");
    }

    /**
     * Uses User' inputs to database in order to create a new user.
     * @param username User's username
     * @param password User's password
     * @param accountID User's unique ID
     * @param balance User's initial balance
     * @param fullName User's first and last name concatenated
     * @param email User's GMU email
     * @param dob User's date of birth
     * @param code User's verification code
     * @param STATUS User's status
     * */
    public void addUser(String username, String password, int accountID, String fullName, String email, String dob, Double balance, int code, int STATUS) {
        try {
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("username", username)
                            .withString("password", password)
                            .withInt("accountID", accountID)
                            .withString("name", fullName)
                            .withString("email", email)
                            .withString("dob", dob)
                            .withDouble("balance", 0.0)
                            .withInt("CODE", code)
                            .withInt("STATUS", STATUS));
        } catch (Exception e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Getter method that returns user's Name
     * @param username User's username
     * @return User's name
     */
    public String getName(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        System.out.println(outcome.getString("name"));
        return outcome.getString("name");
    }

    /**
     * Getter method that returns user's ID
     * @param username User's username
     * @return User's ID
     */
    public int getAccountID(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("accountID");
    }

    /**
     * Getter method that returns user's email
     * @param username User's username
     * @return User's email
     */
    public String getEmail(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("email");

    }

    /**
     * Getter method that returns user's current balance
     * @param username User's username
     * @return User's current balance
     */
    public double getBalance(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getDouble("balance");

    }

    /**
     * Getter method that returns user's date of birth
     * @param username User's username
     * @return User's date of birth
     */
    public String getDOB(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("dob");

    }

    /**
     * Getter method that returns user's password
     * @param username User's username
     * @return User's password
     */
    public String getPassword(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("password");

    }

    /**
     * Getter method that returns user's verification code
     * @param username User's username
     * @return User's verification code
     */
    public int getCODE(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("CODE");

    }

    /**
     * Getter method that returns user's status
     * @param username User's username
     * @return User's status
     */
    public int getSTATUS(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("STATUS");

    }

    /**
     * Updates user's password
     * @param username User's username
     * @param amount User's new balance
     * */
    public void updateBalance(String username, double amount) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set balance = :l")
                .withValueMap(new ValueMap().withString(":l", Double.toString(amount)))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    /**
     * Update's user's password
     * @param username User's username
     * @param password User's new password
     * */
    public void updatePassword(String username, String password) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set password = :l")
                .withValueMap(new ValueMap().withString(":l", password))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    /**
     * Update's user's GMU email address
     * @param username User's username
     * @param email User's new GMU email address
     * */
    public void updateEmail(String username, String email) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set email = :l")
                .withValueMap(new ValueMap().withString(":l", email))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    /**
     * Update's user's status
     * @param username User's username
     * @param status User's new status
     * */
    public void updateSTATUS(String username, int status) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set STATUS = :l")
                .withValueMap(new ValueMap().withInt(":l", status))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    /**
     * Checks if User exists in database based on its username
     * @param username User's username
     * @return Returns true if user exists, false if otherwise
     * */
    public boolean checkDatabase(String username) {

        return false;

    }
    

    /**
     * Deletes user with given username.
     * @param username User's username.
     * */
    public void deleteItem(String username){
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("username", username);
        table.deleteItem(deleteItemSpec);

    }


    public static void main(String[] args) {
        Database e = new Database();
        e.checkDatabase("master");

    }
    }











