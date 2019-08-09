//snippet-sourcedescription:[PutMetricAlarm.java demonstrates how to create a new CloudWatch alarm based on CPU utilization for an instance.]
package com.aws.loginapp.metrics;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.ComparisonOperator;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.EnableAlarmActionsRequest;
import software.amazon.awssdk.services.cloudwatch.model.EnableAlarmActionsResponse;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricAlarmRequest;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricAlarmResponse;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;
import software.amazon.awssdk.services.cloudwatch.model.Statistic;



public class PutMetricAlarm {

	
	public PutMetricAlarmResponse createAlaramForMetric() {
		String alarmName="ServerHealth"; 
		String instanceId="i-07c4cfcad02827750";
		CloudWatchClient cw = CloudWatchClient.builder().build();

		Dimension dimension = Dimension.builder().name("InstanceId").value(instanceId).build();

		PutMetricAlarmRequest request = PutMetricAlarmRequest.builder().alarmName(alarmName)
				.comparisonOperator(ComparisonOperator.GREATER_THAN_THRESHOLD).evaluationPeriods(1)
				.metricName("CPUUtilization").namespace("AWS/EC2").period(60).statistic(Statistic.AVERAGE)
				.threshold(70.0).actionsEnabled(true).alarmActions("arn:aws:sns:us-east-2:050077062646:Error").alarmDescription("Alarm when server CPU utilization exceeds 70%")
				.unit(StandardUnit.SECONDS).dimensions(dimension).build();

		PutMetricAlarmResponse response = cw.putMetricAlarm(request);

		System.out.printf("Successfully created alarm with name %s", alarmName);
		return response;
	}

	/*@Bean
	public DeleteAlarmsResponse deleteAlaramForMetric(String alarmName) {
		String alarm_name = alarmName;

		CloudWatchClient cw = CloudWatchClient.builder().build();

		DeleteAlarmsRequest request = DeleteAlarmsRequest.builder().alarmNames(alarm_name).build();

		DeleteAlarmsResponse response = cw.deleteAlarms(request);

		System.out.printf("Successfully deleted alarm %s", alarm_name);
		return response;

	}*/

	
	
	public EnableAlarmActionsResponse enableAlaramForMetric(String alarmName) {
		CloudWatchClient cw = CloudWatchClient.builder().build();

		EnableAlarmActionsRequest request = EnableAlarmActionsRequest.builder().alarmNames(alarmName).build();

		EnableAlarmActionsResponse response = cw.enableAlarmActions(request);

		System.out.printf("Successfully enabled actions on alarm %s", alarmName);
		return response;

	}
}
