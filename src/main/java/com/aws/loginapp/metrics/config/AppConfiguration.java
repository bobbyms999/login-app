package com.aws.loginapp.metrics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentialsProvider;

@Configuration
public class AppConfiguration {

	@Value("${aws.client.proxy.host:@null}")
	private String clientProxyHost;

	@Value("${aws.client.proxy.port:@null}")
	private Integer clientProxyPort;

	@Value("${aws.client.connection.timeout}")
	private Integer clientConnectionTimeout;

	@Value("${aws.client.max.errorRetry}")
	private Integer clientMaxErrorRetry;

	@Value("${aws.client.useProxy}")
	private Boolean useProxy;

	@Value("${aws.client.protocol}")
	private String clientProtocol;

	@Bean
	public ClientConfiguration awsClientConfig() {
		ClientConfiguration clientConfig = new ClientConfiguration();

		if (useProxy) {
			clientConfig.setProxyHost(clientProxyHost);
			clientConfig.setProxyPort(clientProxyPort);
		}

		clientConfig.setProtocol(Protocol.valueOf(clientProtocol.toUpperCase()));
		clientConfig.setConnectionTimeout(clientConnectionTimeout);
		clientConfig.setMaxErrorRetry(clientMaxErrorRetry);

		return clientConfig;
	}

	@Bean
	public AWSCredentialsProvider credentialsProvider() {
		return new CredentialProviderChain();
	}

}
