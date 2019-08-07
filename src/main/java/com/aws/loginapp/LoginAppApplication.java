package com.aws.loginapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aws.loginapp.events.CreateAWSEvent;
import com.aws.loginapp.events.PutEventRule;

@SpringBootApplication
public class LoginAppApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(LoginAppApplication.class, args);
		new LoginAppApplication().test();
		System.out.println("test...."+new LoginAppApplication().test());
	}

	
	public String test()
	{
		/*PutMetricAlarm alaram=new PutMetricAlarm();
        alaram.createAlaramForMetric();*/
        
        CreateAWSEvent event=new CreateAWSEvent();
        event.createAEvent("Sample_Test");
       
        PutEventRule rule= new PutEventRule();
        rule.createRuleAndTargetForEvent("Sample", "arn:aws:events::rule", "arn:aws:lambda:us-east-2:050077062646:function:lambda-test", "Error");
		return "Success";
	}
	
}
