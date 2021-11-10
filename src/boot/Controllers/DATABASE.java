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



import java.util.Arrays;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;


public class DATABASE {

    public static void main(String[] args) {
        DATABASE e = new DATABASE();
        e.addUser("dkkumar77", "password", 123123123, "Deepak Kumar", "dk.kumar77@yahoo.com", "09-09-2000", 10.0, 1239, 0);

    }

    Table table;


    public DATABASE() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);
        this.table = dynamoDB.getTable("data");

    }

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
     * GETTER METHODS
     *
     * @param username
     * @return
     */
    public String getName(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        System.out.println(outcome.getString("name"));
        return outcome.getString("name");

    }


    public int getAccountID(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("accountID");


    }


    public String getEmail(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("email");

    }


    public double getBalance(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getDouble("balance");

    }


    public String getDOB(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("dob");

    }


    public String getPassword(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getString("password");

    }


    public int getCODE(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("CODE");

    }


    public int getSTATUS(String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("username", username);
        Item outcome = table.getItem(spec);
        return outcome.getInt("STATUS");

    }


    public void updateBalance(String username, double amount) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set balance = :l")
                .withValueMap(new ValueMap().withString(":l", Double.toString(amount)))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    public void updatePassword(String username, String password) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set password = :l")
                .withValueMap(new ValueMap().withString(":l", password))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    public void updateEmail(String username, String email) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set email = :l")
                .withValueMap(new ValueMap().withString(":l", email))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    public void updateSTATUS(String username, String status) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", username)
                .withUpdateExpression("set STATUS = :l")
                .withValueMap(new ValueMap().withString(":l", status))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }


    }






