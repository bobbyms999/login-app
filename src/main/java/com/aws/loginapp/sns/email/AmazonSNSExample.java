package com.aws.loginapp.sns.email;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.aws.loginapp.metrics.config.CredentialProviderChain;

public class AmazonSNSExample {

	public static void main(String[] args) {

		AmazonSNS sns = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2)
				.withCredentials(new CredentialProviderChain()).build();

		try {
	        PublishRequest publishReq = new PublishRequest()
	                .withTopicArn("arn:aws:sns:us-east-2:050077062646:Error")
	                .withMessage("hello welcome to sns");
	        sns.publish(publishReq);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	}

}
