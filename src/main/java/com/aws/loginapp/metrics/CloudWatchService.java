package com.aws.loginapp.metrics;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import javax.annotation.Resource;

@Component
public class CloudWatchService {

    @Resource
    private ClientConfiguration clientConfiguration;

    @Resource
    private AWSCredentialsProvider awsCredentialsProvider;

    /**
     * Defaults to us-west-2
     */
    @Value("${aws.cloudwatch.region: us-east-2}")
    private String awsRegion;

    public void publish(String nameSpace,String metricName,StandardUnit unit,String dimensionName,String dimensionValue,double dataPoint){
        Dimension dimension = new Dimension()
                .withName(dimensionName)
                .withValue(dimensionValue);

        MetricDatum datum = new MetricDatum()
                .withMetricName(metricName)
                .withUnit(unit)
                .withValue(dataPoint)
                .withDimensions(dimension);

        PutMetricDataRequest request = new PutMetricDataRequest()
                .withNamespace(nameSpace)
                .withMetricData(datum);

        PutMetricDataResult response = AmazonCloudWatchClientBuilder
                .standard()
                .withClientConfiguration(clientConfiguration)
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion)
                .build().putMetricData(request);
        
        
        System.out.println("Response Generated..."+response);
    }

}
