package com.aws.loginapp.instancestate;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeSpotInstanceRequestsRequest;
import com.amazonaws.services.ec2.model.DescribeSpotInstanceRequestsResult;
import com.amazonaws.services.ec2.model.SpotInstanceRequest;

public class AWSInstanceState {
	 /**
	     * The areOpen method will determine if any of the requests that were started are still
	     * in the open state. If all of them have transitioned to either active, cancelled, or
	     * closed, then this will return false.
	     * @return
	     */
	    public void  checkInstanceInfo() {
	    	List<String> instanceIds;
	    	 /*
	         * The ProfileCredentialsProvider will return your [default]
	         * credential profile by reading from the credentials file located at
	         * (~/.aws/credentials).
	         */
	        AWSCredentials credentials = null;
	        try {
	            credentials = new ProfileCredentialsProvider().getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (~/.aws/credentials), and is in valid format.",
	                    e);
	        }

	        // Create the AmazonEC2Client object so we can call various APIs.
	        AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
	            .withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .withRegion("us-east-2")
	            .build();
	        //==========================================================================//
	        //============== Describe Spot Instance Requests to determine =============//
	        //==========================================================================//
	
	        // Create the describeRequest with tall of the request id to monitor (e.g. that we started).
	        DescribeSpotInstanceRequestsRequest describeRequest = new DescribeSpotInstanceRequestsRequest();
	        List<String> list=new ArrayList<>();
	        list.add("");
	        describeRequest.setSpotInstanceRequestIds(list);
	
	        System.out.println("Checking to determine if Spot Bids have reached the active state...");
	
	        // Initialize variables.
	        instanceIds = new ArrayList<String>();
	
	        try
	        {
	            // Retrieve all of the requests we want to monitor.
	            DescribeSpotInstanceRequestsResult describeResult = ec2.describeSpotInstanceRequests(describeRequest);
	            List<SpotInstanceRequest> describeResponses = describeResult.getSpotInstanceRequests();
	
	            // Look through each request and determine if they are all in the active state.
	            for (SpotInstanceRequest describeResponse : describeResponses) {
	                System.out.println(" " +describeResponse.getSpotInstanceRequestId() +
	                                   " is in the "+describeResponse.getState() + " state.");
	
	                // If the state is open, it hasn't changed since we attempted to request it.
	                // There is the potential for it to transition almost immediately to closed or
	                // cancelled so we compare against open instead of active.
	                if (describeResponse.getState().equals("open")) {
	                }
	
	                // Add the instance id to the list we will eventually terminate.
	                instanceIds.add(describeResponse.getInstanceId());
	            }
	        } catch (AmazonServiceException e) {
	            // Print out the error.
	            System.out.println("Error when calling describeSpotInstances");
	            System.out.println("Caught Exception: " + e.getMessage());
	            System.out.println("Reponse Status Code: " + e.getStatusCode());
	            System.out.println("Error Code: " + e.getErrorCode());
	            System.out.println("Request ID: " + e.getRequestId());
	
	            // If we have an exception, ensure we don't break out of the loop.
	            // This prevents the scenario where there was blip on the wire.
	        }
	
	    }
}

