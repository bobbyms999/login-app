package com.aws.loginapp.metrics.config;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.AwsSystemPropertyRegionProvider;

public class SysProps extends SystemPropertiesCredentialsProvider {

	private String accessKey = "AKIAQXKGTKX3IQPY6QRX";

	private String secretKey = "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";

	@Override
	public AWSCredentials getCredentials() {

		return new BasicAWSCredentials(this.accessKey, this.secretKey);
	}

}
