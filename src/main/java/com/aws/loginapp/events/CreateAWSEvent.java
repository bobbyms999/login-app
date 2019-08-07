package com.aws.loginapp.events;

import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.cloudwatchevents.model.PutEventsResponse;

public class CreateAWSEvent {

	public static void createAEvent(String resource_arn) {
		
		CloudWatchEventsClient cwe = CloudWatchEventsClient.builder().build();

		final String EVENT_DETAILS = "{ \"key1\": \"value1\", \"key2\": \"value2\" }";

		PutEventsRequestEntry request_entry = PutEventsRequestEntry.builder().detail(EVENT_DETAILS)
				.detailType("Scheduled Event").resources(resource_arn).source("aws.events")
				.build();

		PutEventsRequest request = PutEventsRequest.builder().entries(request_entry).build();

		PutEventsResponse response = cwe.putEvents(request);
		// snippet-end:[cloudwatch.java2.put_events.main]

		System.out.println("Successfully put CloudWatch event");
	}

}