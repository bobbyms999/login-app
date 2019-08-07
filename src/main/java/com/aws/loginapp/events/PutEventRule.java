package com.aws.loginapp.events;

import software.amazon.awssdk.services.cloudwatchevents.CloudWatchEventsClient;
import software.amazon.awssdk.services.cloudwatchevents.model.PutRuleRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.PutRuleResponse;
import software.amazon.awssdk.services.cloudwatchevents.model.PutTargetsRequest;
import software.amazon.awssdk.services.cloudwatchevents.model.PutTargetsResponse;
import software.amazon.awssdk.services.cloudwatchevents.model.RuleState;
import software.amazon.awssdk.services.cloudwatchevents.model.Target;

public class PutEventRule {
	public static void createRuleAndTargetForEvent(String rule_name, String role_arn, String function_arn, String target_id) {

		CloudWatchEventsClient cwe = CloudWatchEventsClient.builder().build();

		//Step-1(Creating a rule for event)
		PutRuleRequest request = PutRuleRequest.builder().name(rule_name)/*.roleArn(role_arn)*/
				.scheduleExpression("rate(5 minutes)").description("Checking the service in other instance by lamda function").state(RuleState.ENABLED).build();

		PutRuleResponse response = cwe.putRule(request);

		//Step-2(Creating a target for a event)
		Target target = Target.builder().arn(function_arn).id("lambda-test").build();

		PutTargetsRequest targetsrequest = PutTargetsRequest.builder().targets(target).rule(rule_name).build();

		PutTargetsResponse targetsresponse = cwe.putTargets(targetsrequest);

		System.out.printf("Successfully created CloudWatch events rule %s with arn %s", rule_name, response.ruleArn());
		System.out.printf("Successfully created CloudWatch events target for rule %s", rule_name,targetsresponse.toString());

	}
}
