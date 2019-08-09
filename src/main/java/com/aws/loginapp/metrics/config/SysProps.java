package com.aws.loginapp.metrics.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;

public class SysProps extends SystemPropertiesCredentialsProvider {

	private String accessKey = "AKIAQXKGTKX3GYSXCH4R";

	private String secretKey = "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";

	@Override
	public AWSCredentials getCredentials() {

		return new BasicAWSCredentials(this.accessKey, this.secretKey);
	}

}
