package com.aws.loginapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aws.loginapp.events.CreateAWSEvent;
import com.aws.loginapp.metrics.PutMetricAlarm;

@SpringBootApplication
public class LoginAppApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(LoginAppApplication.class, args);
		new LoginAppApplication().test();
		System.out.println("test...."+new LoginAppApplication().test());
	}

	
	public String test()
	{
		PutMetricAlarm alaram=new PutMetricAlarm();
        alaram.createAlaramForMetric();
        
        CreateAWSEvent event=new CreateAWSEvent();
        event.createAEvent("Sample Event");
		return "Success";
	}
	
}
